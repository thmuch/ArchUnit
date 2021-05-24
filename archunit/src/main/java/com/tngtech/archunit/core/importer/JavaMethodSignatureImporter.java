/*
 * Copyright 2014-2021 TNG Technology Consulting GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tngtech.archunit.core.importer;

import com.tngtech.archunit.base.Optional;
import com.tngtech.archunit.core.domain.JavaCodeUnit;
import com.tngtech.archunit.core.importer.DomainBuilders.JavaTypeCreationProcess;
import org.objectweb.asm.signature.SignatureReader;
import org.objectweb.asm.signature.SignatureVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.tngtech.archunit.core.importer.ClassFileProcessor.ASM_API_VERSION;

class JavaMethodSignatureImporter {
    private static final Logger log = LoggerFactory.getLogger(JavaMethodSignatureImporter.class);

    static Optional<JavaTypeCreationProcess<JavaCodeUnit>> parseAsmMethodReturnTypeSignature(String signature) {
        if (signature == null) {
            return Optional.absent();
        }

        log.trace("Analyzing method signature: {}", signature);

        SignatureProcessor signatureProcessor = new SignatureProcessor();
        new SignatureReader(signature).accept(signatureProcessor);
        return Optional.fromNullable(signatureProcessor.getMethodReturnType());
    }

    private static class SignatureProcessor extends SignatureVisitor {
        private final GenericMemberTypeProcessor<JavaCodeUnit> genericMethodReturnTypeProcessor = new GenericMemberTypeProcessor<>();

        SignatureProcessor() {
            super(ASM_API_VERSION);
        }

        @Override
        public SignatureVisitor visitReturnType() {
            return genericMethodReturnTypeProcessor;
        }

        JavaTypeCreationProcess<JavaCodeUnit> getMethodReturnType() {
            return genericMethodReturnTypeProcessor.getType();
        }
    }
}
