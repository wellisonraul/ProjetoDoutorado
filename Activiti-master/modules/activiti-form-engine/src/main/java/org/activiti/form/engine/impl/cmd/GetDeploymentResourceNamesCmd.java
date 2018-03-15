/* Licensed under the Apache License, Version 2.0 (the "License");
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
package org.activiti.form.engine.impl.cmd;

import java.io.Serializable;
import java.util.List;

import org.activiti.form.engine.ActivitiFormIllegalArgumentException;
import org.activiti.form.engine.impl.interceptor.Command;
import org.activiti.form.engine.impl.interceptor.CommandContext;

/**
 * @author Joram Barrez
 */
public class GetDeploymentResourceNamesCmd implements Command<List<String>>, Serializable {

  private static final long serialVersionUID = 1L;
  protected String deploymentId;

  public GetDeploymentResourceNamesCmd(String deploymentId) {
    this.deploymentId = deploymentId;
  }

  public List<String> execute(CommandContext commandContext) {
    if (deploymentId == null) {
      throw new ActivitiFormIllegalArgumentException("deploymentId is null");
    }

    return commandContext.getDeploymentEntityManager().getDeploymentResourceNames(deploymentId);
  }

}
