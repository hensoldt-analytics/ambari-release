/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ambari.server.upgrade;

import static org.apache.ambari.server.security.authorization.RoleAuthorization.AMBARI_VIEW_STATUS_INFO;

import java.sql.SQLException;
import java.util.Collections;

import org.apache.ambari.server.AmbariException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * The {@link UpgradeCatalog26219} upgrades Ambari from 2.6.2.0 to 2.6.2.19
 */
public class UpgradeCatalog26219 extends AbstractUpgradeCatalog {

  private final static Logger LOG = LoggerFactory.getLogger(UpgradeCatalog26219.class);

  @Inject
  public UpgradeCatalog26219(Injector injector) {
    super(injector);
  }

  @Override
  public String getSourceVersion() {
    return "2.6.2";
  }

  @Override
  public String getTargetVersion() {
    return "2.6.2.19";
  }

  @Override
  protected void executeDDLUpdates() throws AmbariException, SQLException {
  }

  @Override
  protected void executePreDMLUpdates() throws AmbariException, SQLException {
  }

  @Override
  protected void executeDMLUpdates() throws AmbariException, SQLException {
    createRoleAuthorizations();
  }

  protected void createRoleAuthorizations() throws SQLException {
    addRoleAuthorization(AMBARI_VIEW_STATUS_INFO.getId(), "View status information", Collections.singleton("AMBARI.ADMINISTRATOR:AMBARI"));
    LOG.info("Added new role authorization {}", AMBARI_VIEW_STATUS_INFO.getId());
  }
}
