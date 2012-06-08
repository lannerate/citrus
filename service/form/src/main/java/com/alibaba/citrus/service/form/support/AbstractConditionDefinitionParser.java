/*
 * Copyright (c) 2002-2012 Alibaba Group Holding Limited.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.citrus.service.form.support;

import static com.alibaba.citrus.springext.util.SpringExtUtil.*;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import com.alibaba.citrus.springext.support.parser.AbstractSingleBeanDefinitionParser;

public class AbstractConditionDefinitionParser<C extends AbstractCondition> extends
        AbstractSingleBeanDefinitionParser<C> {
    @Override
    protected final void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        // attributes
        doParseAttributes(element, parserContext, builder);

        // element
        doParseElement(element, parserContext, builder);
    }

    protected void doParseAttributes(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        attributesToProperties(element, builder);
    }

    protected void doParseElement(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
    }
}
