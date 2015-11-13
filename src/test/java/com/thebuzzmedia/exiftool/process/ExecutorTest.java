/**
 * Copyright 2015 Mickael Jeanroy <mickael.jeanroy@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thebuzzmedia.exiftool.process;

import org.junit.Test;

import java.io.File;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExecutorTest {

	@Test
	public void it_should_execute_command_line() {
		File script = new File(getClass().getResource("/processes/success.sh").getFile());
		Command command = createUnixCommand(script.getAbsolutePath());

		Executor executor = new Executor();
		Result result = executor.execute(command);

		assertThat(result).isNotNull();
		assertThat(result.getExitStatus()).isZero();
		assertThat(result.getOutput()).isEqualTo("Hello World");
	}

	private Command createUnixCommand(String script) {
		Command command = mock(Command.class);
		when(command.getExecutable()).thenReturn("/bin/sh");
		when(command.getArguments()).thenReturn(asList(
			script
		));

		return command;
	}
}