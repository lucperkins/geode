/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.geode.cache.query.internal.cq;

import org.apache.geode.cache.query.CqQuery;
import org.apache.geode.cache.query.CqServiceStatistics;
import org.apache.geode.cache.query.internal.DefaultQueryService;

/**
 * Provides statistical information about CqService.
 *
 * @since GemFire 5.5
 */
public class CqServiceStatisticsImpl implements CqServiceStatistics {

  private CqServiceImpl cqService;

  /**
   * Constructor for CqStatisticsImpl
   *
   * @param cqs - CqService
   */
  CqServiceStatisticsImpl(CqServiceImpl cqs) {
    cqService = cqs;
  }

  /**
   * Returns the number of CQs currently executing
   */
  @Override
  public long numCqsActive() {
    return this.cqService.getCqServiceVsdStats().getNumCqsActive();
  }

  /**
   * Returns number of CQs created.
   *
   * @return long number of cqs created.
   */
  @Override
  public long numCqsCreated() {
    return this.cqService.getCqServiceVsdStats().getNumCqsCreated();
  }

  /**
   * Returns number of Cqs that are closed.
   */
  @Override
  public long numCqsClosed() {
    return this.cqService.getCqServiceVsdStats().getNumCqsClosed();
  }

  /**
   * Returns number of Cqs that are stopped.
   */
  @Override
  public long numCqsStopped() {
    return this.cqService.getCqServiceVsdStats().getNumCqsStopped();
  }

  /**
   * Returns number of CQs created from the client.
   */
  @Override
  public long numCqsOnClient() {
    return this.cqService.getCqServiceVsdStats().getNumCqsOnClient();
  }

  /**
   * Returns the number of CQs (active + suspended) on the given region.
   */
  @Override
  public long numCqsOnRegion(String regionName) {
    DefaultQueryService queryService =
        (DefaultQueryService) cqService.getInternalCache().getLocalQueryService();
    try {
      CqQuery[] cqs = queryService.getCqs(regionName);

      if (cqs != null) {
        return cqs.length;
      }
    } catch (Exception ex) {
      // Dont do anything.
    }
    return 0;
  }
}
