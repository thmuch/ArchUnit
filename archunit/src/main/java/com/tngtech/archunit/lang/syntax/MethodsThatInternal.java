/*
 * Copyright 2014-2025 TNG Technology Consulting GmbH
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
package com.tngtech.archunit.lang.syntax;

import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.lang.syntax.elements.MethodsThat;

class MethodsThatInternal
        extends CodeUnitsThatInternal<JavaMethod, GivenMethodsInternal>
        implements MethodsThat<GivenMethodsInternal> {

    MethodsThatInternal(GivenMethodsInternal givenMethods, PredicateAggregator<JavaMethod> currentPredicate) {
        super(givenMethods, currentPredicate);
    }
}
