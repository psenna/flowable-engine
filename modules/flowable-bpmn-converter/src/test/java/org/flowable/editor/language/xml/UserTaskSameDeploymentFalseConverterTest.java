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
package org.flowable.editor.language.xml;

import static org.assertj.core.api.Assertions.assertThat;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.UserTask;
import org.junit.Test;

/**
 * @author Filip Hrisafov
 */
public class UserTaskSameDeploymentFalseConverterTest extends AbstractConverterTest {

    @Override
    protected String getResource() {
        return "userTaskSameDeploymentFalse.bpmn";
    }

    @Test
    public void convertXMLToModel() throws Exception {
        BpmnModel bpmnModel = readXMLFile();
        validateModel(bpmnModel);
    }

    @Test
    public void convertModelToXML() throws Exception {
        BpmnModel bpmnModel = readXMLFile();
        BpmnModel parsedModel = exportAndReadXMLFile(bpmnModel);
        validateModel(parsedModel);
    }

    protected void validateModel(BpmnModel model) {
        FlowElement flowElement = model.getMainProcess().getFlowElement("userTask");
        assertThat(flowElement)
                .isInstanceOfSatisfying(UserTask.class, task -> {
                    assertThat(task.getId()).isEqualTo("userTask");
                    assertThat(task.getFormKey()).isEqualTo("testKey");
                    assertThat(task.isSameDeployment()).isFalse();
                });
        assertThat(flowElement.getAttributes()).isEmpty();
    }
}