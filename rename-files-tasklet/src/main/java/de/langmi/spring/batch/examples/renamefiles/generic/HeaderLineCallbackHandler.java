/*
 * Copyright 2011 Michael R. Lange <michael.r.lange@langmi.de>.
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
package de.langmi.spring.batch.examples.renamefiles.generic;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.LineCallbackHandler;

/**
 * HeaderLineCallbackHandler handles header line from file, do not use with 
 * more than one header line.
 *
 * @author Michael R. Lange <michael.r.lange@langmi.de>
 */
public class HeaderLineCallbackHandler implements LineCallbackHandler {

    private ConcurrentHashMap<String, String> fileNamesMap;
    private StepExecution stepExecution;

    /**
     * Handles header line and saves business key to step execution context.
     * 
     * @param line 
     */
    @Override
    public void handleLine(String line) {
        // extract filename
        String fileName = stepExecution.getExecutionContext().getString(BatchConstants.CONTEXT_NAME_INPUT_FILE);
        // put desired fileName        
        this.fileNamesMap.put(fileName, "output-" + line + ".txt");
    }

    @BeforeStep
    public void setStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    public void setFileNamesMap(ConcurrentHashMap<String, String> fileNamesMap) {
        this.fileNamesMap = fileNamesMap;
    }
}
