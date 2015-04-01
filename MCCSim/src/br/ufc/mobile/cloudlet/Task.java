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



package br.ufc.mobile.cloudlet;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;

import br.ufc.mobile.offload.OffloadStatus;

/**
 * This class represents a mobileCloudlet. Basically, it is a cloudlet but with
 * additional parameters such as offloadStatus (STATIC OR DYNAMIC),
 * computationSize and more attributes that offload models might need to analyse
 * it.
 * 
 * @author sergio
 *
 */
public class Task extends Cloudlet {
	private OffloadStatus offloadStatus;
	
	private double transferUploadDelay;
	private double transferDownloadDelay;

	private static final double defaultInstructionSize = 10;
	private double dataInputSize;

	private double computationSize;

	private long timeExecution;

	private Task(int cloudletId, long cloudletLength, int pesNumber,
			long cloudletFileSize, long cloudletOutputSize,
			UtilizationModel utilizationModelCpu,
			UtilizationModel utilizationModelRam,
			UtilizationModel utilizationModelBw) {
		super(cloudletId, cloudletLength, pesNumber, cloudletFileSize,
				cloudletOutputSize, utilizationModelCpu, utilizationModelRam,
				utilizationModelBw);
	}

	public Task(int cloudletId, double computationSize,
			long cloudletInputSize, long cloudletOutputSize) {
		this(cloudletId, 0l, 1, cloudletInputSize, cloudletOutputSize,
				new UtilizationModelFull(), new UtilizationModelFull(),
				new UtilizationModelFull());
		setInstructionBasedOf(cloudletInputSize);
		setComputationSize(computationSize);
	}

	private void setInstructionBasedOf(double dataInputSize) {
		long cloudletLength = 0;
		cloudletLength = (long) (defaultInstructionSize * dataInputSize);
		setCloudletLength(cloudletLength);
		this.dataInputSize = dataInputSize;
	}

	public OffloadStatus getOffloadStatus() {
		return offloadStatus;
	}

	public double getCloudletInputDataLength() {
		return dataInputSize;
	}

	public double getCloudletOutputDataLength() {
		return getCloudletOutputSize();
	}

	/**
	 * 
	 * @param offloadStatus
	 *            . true means that offloading is mandatory, false means that
	 *            offload´s possibility will be checked by an offload non-static
	 *            model
	 */
	public void setOffloadStatus(OffloadStatus offloadStatus) {
		this.offloadStatus = offloadStatus;
	}

	public double getTransferUploadTime() {
		return transferUploadDelay;
	}

	public double getTransferDownloadTime() {
		return transferDownloadDelay;
	}

	public void setTransferDownloadDelay(double transferDownloadDelay) {
		this.transferDownloadDelay = transferDownloadDelay;
	}

	public void setTransferUploadDelay(double transferUploadDelay) {
		this.transferUploadDelay = transferUploadDelay;
	}

	public double getComputationSize() {
		return computationSize;
	}

	public void setComputationSize(double computationSize) {
		this.computationSize = computationSize;
	}

	public void setLocalTime(long executionTimeOnDevice) {
		this.timeExecution = executionTimeOnDevice;

	}
	
	public long getTime(){
		return timeExecution;
	}

}
