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



package br.ufc.mobile.rstructure;

import java.lang.reflect.Field;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

class RTool {
	private Rengine rEngine;
	private static RTool rTool;

	private RTool() {
		configRLibOnJava();
		rEngine = new Rengine(new String[] { "--vanilla" }, false, null);
		//installLibraries();
		rEngine.eval("library(\"fExtremes\")");
		rEngine.eval("library(\"statmod\")");
		rEngine.eval("library(\"FAdist\")");
		rEngine.eval("library(\"actuar\")");
		rEngine.eval("library(\"VGAM\")");
	}

	protected static RTool getInstance() {
		if (rTool == null) {
			rTool = new RTool();
		}
		return rTool;
	}

	private void configRLibOnJava() {
		System.setProperty("java.library.path",
				"C:/Users/sergio/Documents/R/win-library/3.1/rJava/jri/x64/");
		Field fieldSysPath;
		try {
			fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
			fieldSysPath.setAccessible(true);
			fieldSysPath.set(null, null);
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public REXP callScript(String fileName, String number, String company,
			String location, String networkType, String streamDataDirection) {
		String scriptPath = this.getClass().getResource("/r/" + fileName)
				.getPath();
		REXP rExp = null;
		scriptPath = scriptPath.substring(1, scriptPath.length());
		scriptPath = scriptPath.replace("/", "\\\\");

		rEngine.eval("source(\"" + scriptPath + "\")");
		

		rExp = rEngine.idleEval("networkRandomGenerator(" + number + " ,\""
				+ company + "." + location + "." + networkType + "."
				+ streamDataDirection + "\")");

		return rExp;
	}

	public boolean initRPackage() {
		return rEngine.waitForR();
	}

	public REXP executeCommand(String cmd) {
		REXP ret = null;
		if (rEngine != null) {
			synchronized (rEngine) {
				ret = rEngine.eval(cmd);
			}
		}
		return ret;

	}

	public void finalizeRPackageExecution() {
		rEngine.end();
	}

	private void installLibraries() {
		executeCommand("install.packages(\"fExtremes\")");
		executeCommand("install.packages(\"statmod\")");
		executeCommand("install.packages(\"FAdist\")");
		executeCommand("install.packages(\"actuar\")");
		executeCommand("install.packages(\"VGAM\")");
	}

	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		RTool rTool = new RTool();
		System.out.println("init R package: " + rTool.initRPackage());

		System.out.println(rTool.executeCommand("1+1"));

		// rTool.callScript("networkRandomGenerator.R", "oi", "aero",
		// "3g","up");
		// rTool.testParametersCombination();

		// System.out.println(rTool.executeCommand("setwd(\"C:\\\\Users\\\\sergio\\\\Documents\")"));
		// System.out.println(rTool.executeCommand("getwd()"));

		 System.out.println(rTool.executeCommand("library(\"fExtremes\")"));
		 System.out.println(rTool.executeCommand("library(\"fGarch\")"));
		 System.out.println(rTool.executeCommand("library(\"fTrading\")"));
		 System.out
		 .println(rTool
		 .executeCommand("mi.teo <- rgev(1, xi = -0.86253, mu = 28.825 , beta =3.7849)"));
		//
		// System.out
		// .println(rTool
		// .executeCommand("mi.teo <-rlnorm3(1,shape=0.56169,scale=3.2526,thres=42.952)"));

		rTool.finalizeRPackageExecution();
	}

	public void testParametersCombination() {
		String companies[] = new String[] { "oi", "tim", "claro", "vivo" };
		String locations[] = new String[] { "pici", "aero" };
		String networkTypes[] = new String[] { "3g", "4g" };
		String dataStreamDirections[] = new String[] { "up", "down" };
		for (String company : companies) {
			for (String location : locations) {
				for (String networkType : networkTypes) {
					for (String dataStreamDirection : dataStreamDirections) {
						System.out.print("parametros: " + company + " "
								+ location + " " + networkType + " "
								+ dataStreamDirection);
						System.out.println(" - "
								+ callScript("networkRandomGenerator.R", "100",
										company, location, networkType,
										dataStreamDirection));
					}
				}

			}
		}

	}
}
