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

package com.alibaba.citrus.service.template.support;

import static com.alibaba.citrus.test.TestUtil.*;
import static com.alibaba.citrus.util.CollectionUtil.*;
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class MappedTemplateContextTests {
    private Map<String, Object> map;
    private MappedTemplateContext context;

    @Before
    public void init() {
        map = createHashMap();
        context = new MappedTemplateContext(map);

        assertSame(map, context.getMap());

        map.put("aaa", 111);
        map.put("bbb", 222);
        map.put("ccc", 333);
    }

    @Test
    public void newInstance_null() {
        try {
            new MappedTemplateContext(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e, exception("map"));
        }
    }

    @Test
    public void newInstance() {
        context = new MappedTemplateContext();
        assertNotNull(context.getMap());
    }

    @Test
    public void containsKey() {
        assertEquals(true, context.containsKey("aaa"));
        assertEquals(true, context.containsKey("bbb"));
        assertEquals(true, context.containsKey("ccc"));

        assertEquals(false, context.containsKey("ddd"));

        context.put("ddd", 444);
        assertEquals(true, context.containsKey("ddd"));
    }

    @Test
    public void get() {
        assertEquals(111, context.get("aaa"));
        assertEquals(222, context.get("bbb"));
        assertEquals(333, context.get("ccc"));

        assertEquals(null, context.get("ddd"));

        context.put("ddd", 444);
        assertEquals(444, context.get("ddd"));
    }

    @Test
    public void put() {
        context.put("aaa", 1111);
        context.put("bbb", 2222);
        context.put("ccc", 3333);
        context.put("ddd", 4444);

        assertEquals(1111, context.get("aaa"));
        assertEquals(2222, context.get("bbb"));
        assertEquals(3333, context.get("ccc"));
        assertEquals(4444, context.get("ddd"));
    }

    @Test
    public void keySet() {
        List<String> keys = createArrayList(context.keySet());

        Collections.sort(keys);

        assertArrayEquals(new String[] { "aaa", "bbb", "ccc" }, keys.toArray(new String[keys.size()]));

        context.put("ddd", 444);
        keys = createArrayList(context.keySet());

        Collections.sort(keys);

        assertArrayEquals(new String[] { "aaa", "bbb", "ccc", "ddd" }, keys.toArray(new String[keys.size()]));
    }

    @Test
    public void remove() {
        context.remove("aaa");
        context.remove("bbb");
        context.remove("ccc");
        context.remove("ddd");

        assertEquals(false, context.containsKey("aaa"));
        assertEquals(false, context.containsKey("bbb"));
        assertEquals(false, context.containsKey("ccc"));
        assertEquals(false, context.containsKey("ddd"));
    }

    @Test
    public void _toString() {
        String str = context.toString();
        String result = "";

        result = "MappedTemplateContext {\n";
        result += "  [1/3] aaa = 111\n";
        result += "  [2/3] bbb = 222\n";
        result += "  [3/3] ccc = 333\n";
        result += "}";

        assertEquals(result, str);
    }
}
