/*
 * Copyright 2013-2018 SCALEFOCUS (http://scalefocus.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.scalefocus;

import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;
import com.scalefocus.apitools.validation.JsonCompareKeywords;
import com.scalefocus.sqat_java.core.foundation.AbstractTest;
import com.scalefocus.sqat_java.core.foundation.api.http.HttpResponseStatusType;
import com.scalefocus.sqat_java.core.foundation.utils.ownership.MethodOwner;
import com.scalefocus.sqat_java.core.foundation.utils.tag.Priority;
import com.scalefocus.sqat_java.core.foundation.utils.tag.TestPriority;
import com.scalefocus.api.DeleteUserMethod;
import com.scalefocus.api.GetUserMethods;
import com.scalefocus.api.PostUserMethod;

/**
 * This sample shows how create REST API tests.
 *
 * @author sqat_java
 */
public class APISampleTest extends AbstractTest {

    @Test(description = "JIRA #DEMO-0001")
    @MethodOwner(owner = "scalefocus")
    public void testCreateUser() throws Exception {
        setCases("4555,54545");
        PostUserMethod api = new PostUserMethod();
        api.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        api.callAPI();
        api.validateResponse();
    }

    @Test(description = "JIRA #DEMO-0002")
    @MethodOwner(owner = "scalefocus")
    public void testCreateUserMissingSomeFields() throws Exception {
        PostUserMethod api = new PostUserMethod();
        api.getProperties().remove("name");
        api.getProperties().remove("username");
        api.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        api.callAPI();
        api.validateResponse();
    }

    @Test(description = "JIRA #DEMO-0003")
    @MethodOwner(owner = "scalefocus")
    public void testGetUsers() {
        GetUserMethods getUsersMethods = new GetUserMethods();
        getUsersMethods.expectResponseStatus(HttpResponseStatusType.OK_200);
        getUsersMethods.callAPI();
        getUsersMethods.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getUsersMethods.validateResponseAgainstJSONSchema("api/users/_get/rs.schema");
    }

    @Test(description = "JIRA #DEMO-0004")
    @MethodOwner(owner = "scalefocus")
    @TestPriority(Priority.P1)
    public void testDeleteUsers() {
        DeleteUserMethod deleteUserMethod = new DeleteUserMethod();
        deleteUserMethod.expectResponseStatus(HttpResponseStatusType.OK_200);
        deleteUserMethod.callAPI();
        deleteUserMethod.validateResponse();
    }

}
