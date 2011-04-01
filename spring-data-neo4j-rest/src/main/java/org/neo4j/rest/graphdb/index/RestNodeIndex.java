/**
 * Copyright 2011 the original author or authors.
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

package org.neo4j.rest.graphdb.index;

import org.neo4j.graphdb.Node;
import org.neo4j.rest.graphdb.RestGraphDatabase;
import org.neo4j.rest.graphdb.RestNode;
import org.neo4j.rest.graphdb.RestRequest;

import java.util.Map;

/**
 * @author mh
 * @author Andres Taylor <andres@neotechnology.com>
 * @since 24.01.11
 */
public class RestNodeIndex extends RestIndex<Node>
{
    public RestNodeIndex( RestRequest restRequest, String indexName, RestGraphDatabase restGraphDatabase )
    {
        super( restRequest, indexName, restGraphDatabase );
    }

    public Class<Node> getEntityType()
    {
        return Node.class;
    }

    protected Node createEntity( Map<?, ?> item )
    {
        return new RestNode( (Map<?, ?>)item, restGraphDatabase );
    }
}
