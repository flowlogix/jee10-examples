/*
 * Copyright (C) 2011-2022 Flow Logix, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.flowlogix.examples.shiro;

import static com.flowlogix.examples.shiro.StatisticsResource.increment;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author lprimak
 */
@ViewScoped
@Named
@RequiresUser
@Slf4j
public class ProtectedOmniViewScopedBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final AtomicInteger INSTANCE_COUNT = new AtomicInteger();
    private final int count = INSTANCE_COUNT.incrementAndGet();

    @PostConstruct
    void postConstruct() {
        increment("pc_ofv");
    }

    @PreDestroy
    void preDestroy() {
        increment("pd_ofv");
    }

    public String hello() {
        return String.format("Hello from OmniViewScoped %s - %s", count,
        FacesContext.class.getPackage().getImplementationVersion());
    }
}
