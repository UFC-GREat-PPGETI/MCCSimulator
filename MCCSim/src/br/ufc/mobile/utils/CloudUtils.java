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


//
//package br.ufc.mobile.utils;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.text.DecimalFormat;
//import java.util.List;
//
//import org.cloudbus.cloudsim.Cloudlet;
//import org.cloudbus.cloudsim.Log;
//
//import br.ufc.mobile.cloudlet.MobTask;
//import experiments.Experiment1Class.ImageSize;
//import experiments.Experiment1Class.Smartphone;
//import experiments.Experiment1Class.VmInstance;
//import experiments.ExperimentResultData;
//
//public class CloudUtils {
//
//	public static void printMobileCloudletReturnedList(
//			List<? extends Cloudlet> list) {
//		int size = list.size();
//		MobTask cloudlet;
//
//		String indent = "    ";
//		Log.printLine();
//		Log.printLine("========== OUTPUT ==========");
//		Log.printLine("Cloudlet ID" + indent + "STATUS" + indent
//				+ "Data center ID" + indent + "VM ID" + indent
//				+ "Transfer time. Device -> Cloud" + indent
//				+ "Transfer time. Cloud -> Device" + indent + "Processing Time"
//				+ indent + "Start Time" + indent + "Finish Time");
//
//		DecimalFormat dft = new DecimalFormat("###.##");
//		for (int i = 0; i < size; i++) {
//			cloudlet = (MobTask) list.get(i);
//			Log.print(indent + cloudlet.getCloudletId() + indent + indent);
//
//			if (cloudlet.getCloudletStatus() == Cloudlet.SUCCESS) {
//				Log.print("SUCCESS");
//
//				Log.printLine(indent
//						+ indent
//						+ cloudlet.getResourceId()
//						+ indent
//						+ indent
//						+ indent
//						+ cloudlet.getVmId()
//						+ indent
//						+ indent
//						+ indent
//						+ indent
//						+ dft.format(cloudlet.getTransferUploadTime())
//						+ indent
//						+ indent
//						+ indent
//						+ indent
//						+ indent
//						+ indent
//						+ indent
//						+ indent
//						+ indent
//						+ dft.format(cloudlet.getTransferDownloadTime())
//						+ indent
//						+ indent
//						+ indent
//						+ indent
//						+ indent
//						+ indent
//						+ dft.format(cloudlet.getActualCPUTime())
//						+ indent
//						+ indent
//						+ indent
//						+ indent
//						+ dft.format(cloudlet.getTransferUploadTime()
//								+ cloudlet.getExecStartTime())
//						+ indent
//						+ indent
//						+ dft.format(cloudlet.getTransferUploadTime()
//								+ cloudlet.getTransferDownloadTime()
//								+ cloudlet.getExecStartTime()
//								+ cloudlet.getFinishTime()));
//			}
//		}
//	}
//
//	public static synchronized ExperimentResultData getExperimentResultDataMobileCloudletReturnedListOnFile(
//			int id, ImageSize imageSize, VmInstance vmInstance,
//			Smartphone smartphone, List<? extends Cloudlet> list) {
//		ExperimentResultData experimentResultData = null;
//		int size = list.size();
//		printMobileCloudletReturnedList(list);
//		DecimalFormat dft = new DecimalFormat("###.##");
//		MobTask cloudlet;
//
//		for (int i = 0; i < size; i++) {
//			cloudlet = (MobTask) list.get(i);
//
//			if (cloudlet.getCloudletStatus() == Cloudlet.SUCCESS) {
//
//				experimentResultData = composeMatrixLine(id, imageSize,
//						vmInstance, smartphone, cloudlet, dft);
//
//			}
//		}
//		return experimentResultData;
//	}
//
//	private static ExperimentResultData composeMatrixLine(int id,
//			ImageSize imageSize, VmInstance vmInstance, Smartphone smartphone,
//			MobTask cloudlet, DecimalFormat dft) {
//		ExperimentResultData experimentResultData = new ExperimentResultData();
//		experimentResultData.setId(id);
//		StringBuilder stringBuilder = new StringBuilder();
//		stringBuilder.append("\n");
//		stringBuilder.append(Integer.toString(experimentResultData.getId())
//				+ ";");
//		stringBuilder.append(imageSize.getCellName() + ";");
//		if (vmInstance != null) {
//			stringBuilder.append(vmInstance.name() + ";");
//		}
//		if (smartphone != null) {
//			stringBuilder.append(smartphone.getCellName() + ";");
//		}
//
//		stringBuilder.append(dft.format(cloudlet.getActualCPUTime()) + ";");
//		stringBuilder.append(dft.format(cloudlet.getTransferDownloadTime())
//				+ ";");
//		stringBuilder.append(cloudlet.getTransferUploadTime() + ";");
//
//		stringBuilder.append(dft.format(cloudlet.getTransferUploadTime()
//				+ cloudlet.getTransferDownloadTime()
//				+ cloudlet.getExecStartTime() + cloudlet.getFinishTime()));
//		experimentResultData.setImageSize(imageSize);
//		experimentResultData.setSmartphone(smartphone);
//		experimentResultData.setVmInstance(vmInstance);
//		experimentResultData.setTotalTime(cloudlet.getTransferUploadTime()
//				+ cloudlet.getTransferDownloadTime()
//				+ cloudlet.getExecStartTime() + cloudlet.getFinishTime());
//		experimentResultData.setCpuTime(cloudlet.getActualCPUTime());
//		experimentResultData
//				.setDownloadTime(cloudlet.getTransferDownloadTime());
//		experimentResultData.setUploadTime(cloudlet.getTransferUploadTime());
//		experimentResultData.setLine(stringBuilder.toString().replaceAll(",",
//				"."));
//
//		return experimentResultData;
//	}
//}
