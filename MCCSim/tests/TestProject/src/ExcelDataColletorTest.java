/*******************************************************************************
 *
 *  *  * Copyright (C) 2015 Luis Sérgio da Silva Júnior
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *******************************************************************************/

import java.util.List;

import br.ufc.mobile.devices.DeviceType;
import junit.framework.TestCase;
import experiments.ExcelDataCollector;
import experiments.Experiment1Class;
import experiments.Experiment1Class.ImageSize;
import experiments.Experiment1Class.Smartphone;
import experiments.Experiment1Class.VmInstance;
import experiments.Row;
import experiments.StatisticUtils;

public class ExcelDataColletorTest extends TestCase {
	private ExcelDataCollector excelDataCollector;
	
	@Override
	protected void setUp() throws Exception {
		excelDataCollector = new ExcelDataCollector();
		excelDataCollector.collectDataFromFile("all_results.xls");

		super.setUp();
	}
	
	
	public void testFoo() {
		List<Row> rows = excelDataCollector.getRowsList();

		assertNotNull(rows);

		assertTrue(new Row().setPhoto("img5.jpg").equals(rows.get(1)));

	}

	public void testRowSelection() {

		List<Row> rows = excelDataCollector.getRowsList();

		assertNotNull(rows);
		assertEquals(257,rows.size());
	
		List<Row> resultRows = excelDataCollector.search(rows, new Row().setPhoto("img5.jpg").setTamanho("1MP").setCPU_Time("2700"));
		assertEquals(1,resultRows.size());
		
		}
	
	public void testMedianCalculusOfRowParameters(){
		List<Row> rows= excelDataCollector.getRowsList();
		
		assertNotNull(rows);
		
		List<Row> resultRows = excelDataCollector.search(rows,new Row().setLocal("Cloudlet C2D T5500").setTamanho("4MP"));
		
		assertEquals(6,resultRows.size());
		
		
		
		assertEquals(9943.0d,StatisticUtils.calculateMedian(resultRows.get(0).getCPU_Time(),
				resultRows.get(1).getCPU_Time(),
				resultRows.get(2).getCPU_Time(),
				resultRows.get(3).getCPU_Time(),
				resultRows.get(4).getCPU_Time(),
				resultRows.get(5).getCPU_Time()));
		
	}
	
	
	public void testRunSimulation(){
		Experiment1Class experimentClass = new Experiment1Class();
		//experimentClass.runSimulation(ImageSize.MP_4,VmInstance.LTE_MICRO, Smartphone.LG_E977);
	}
	
}
