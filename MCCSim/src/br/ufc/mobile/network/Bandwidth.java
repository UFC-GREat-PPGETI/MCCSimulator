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



package br.ufc.mobile.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class Bandwidth {
	protected List<Double> uploadValues = new ArrayList<Double>(); // KByte
																	// value
	protected List<Double> downloadValues = new ArrayList<Double>(); // KByte
																		// value
	protected String name;

	public String getLabel() {
		return name;
	}

	public void setLabel(String label) {
		this.name = label;
	}

	public abstract long getCurrentLatency();

	public final double getKByteCurrentDownloadBandwidth() {
		int randomicIndex = new Random().nextInt(downloadValues.size());

		return downloadValues.get(randomicIndex);
	}

	public final double getKByteCurrentUploadBandwidth() {
		int randomicIndex = new Random().nextInt(uploadValues.size());

		return uploadValues.get(randomicIndex);
	}

	public abstract double getMByteCurrentDownloadBandwidth();

	public abstract double getMByteCurrentUploadBandwidth();

	public abstract double getGByteCurrentDownloadBandwidth();

	public abstract double getGByteCurrentUploadBandwidth();

	public abstract double getByteCurrentDownloadBandwidth();

	public abstract double getByteCurrentUploadBandwidth();

	protected final List<Double> getValuesFromDataFile(String fileName) {
		String filePath = this.getClass()
				.getResource("/network_data/" + fileName).getPath();

		Pattern numberPattern = Pattern.compile("[\\d]+,?[\\d]+");
		Scanner scanner = null;
		List<Double> transferRate = new ArrayList<Double>();

		scanner = new Scanner(this.getClass().getResourceAsStream(
				"/network_data/" + fileName));
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			if (numberPattern.matcher(line).find()) {
				transferRate.add(Double.parseDouble(line));
			}

		}

		scanner.close();

		return transferRate;
	}
}
