/*
 * (c) Copyright 2019 Palantir Technologies Inc. All rights reserved.
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

package com.palantir.metric.schema.datadog.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import org.immutables.value.Value;

/** DataDog dashboard schema as per https://docs.datadoghq.com/graphing/graphing_json/. */
@Value.Immutable
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(as = ImmutableDataDogDashboard.class)
@JsonSerialize(as = ImmutableDataDogDashboard.class)
public interface DataDogDashboard {

    String title();

    @Value.Default
    default String description() {
        return "Dashboard generated by Gradle Metric Schema";
    }

    @JsonProperty("layout_type")
    LayoutType layoutType();

    @JsonProperty("is_read_only")
    boolean readOnly();

    @JsonProperty("template_variables")
    List<TemplateVariable> templateVariables();

    @JsonProperty("notify_list")
    List<String> notifyList();

    List<Widget> widgets();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableDataDogDashboard.Builder {}
}
