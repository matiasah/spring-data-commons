/*
 * Copyright 2016-2018 the original author or authors.
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
package org.springframework.data.querydsl;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.data.querydsl.QuerydslUtils.*;

import org.junit.Test;

/**
 * Unit tests for {@link QuerydslUtils}.
 *
 * @author Oliver Gierke
 */
public class QuerydslUtilsUnitTests {

	@Test // DATACMNS-883
	public void rendersDotPathForPathTraversalContainingAnyExpression() {
		assertThat(QuerydslUtils.toDotPath(QUser.user.addresses.any().street)).isEqualTo("addresses.street");
	}

	@Test // DATACMNS-941
	public void skipsIntermediateDelegates() {

		assertThat(toDotPath(QUser.user.as(QSpecialUser.class).as(QSpecialUser.class).specialProperty))
				.isEqualTo("specialProperty");
		assertThat(toDotPath(QUser.user.as(QSpecialUser.class).specialProperty)).isEqualTo("specialProperty");
	}
}
