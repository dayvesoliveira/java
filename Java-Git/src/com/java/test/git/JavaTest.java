package com.java.test.git;

import java.math.BigDecimal;


public class JavaTest {
	
	public static void main(String[] args) {
		
		String v = "<f:bundle baseName=\"Bundle\" bundleName=\"bundle\"/>" +
				"<f:body><f:contentPanel>";
		
		int init = v.indexOf("<f:body");
		int end  = v.indexOf(">");
		if (end <= init) {
			end  = v.indexOf(">", init);
		}
		
		String v2 = v.substring(init, end+1)
			 .replace(">", " sequencial=\"\">");
		
		v = v.replace(v.substring(init, end+1), v2);
		
		String f = "com.constante.CasaArredondamentos";
		
		f = f.substring(f.lastIndexOf(".")+1, f.length());
		
		System.out.println(f);
		
		String fileName = "\\file:\\c\\:content\\teste.txt.zip";
		
        int x = -1;
        v = removeExtension(fileName);
		
		System.out.println(v);
		
		/*NumberUtils numberUtils = new NumberUtils();
		BigDecimal percQtdSimulada = BigDecimal.ZERO;
		Long qtdSimulada = -41L;
		Long qtdeSimuladaTotal = -99L;
		
		percQtdSimulada = new BigDecimal((qtdSimulada.doubleValue() / qtdeSimuladaTotal.doubleValue()) * 100);
		System.out.println(numberUtils.round(percQtdSimulada, 2));*/
		
		
		/*NumberUtils numberUtils = new NumberUtils();
		
		BigDecimal vrCobertura = new BigDecimal(3410.25);
		BigDecimal vrFinanciado = new BigDecimal(149.90);
		
		System.out.println("Cobertura: "   + calcularValorCobertura(vrCobertura, vrFinanciado, numberUtils));
		System.out.println("Qtde Parc. Cobertura: "   + calcularValorParcCobertura(new BigDecimal(350), new BigDecimal(145.20), numberUtils));
		System.out.println("Qtde Cobertura: "   + calcularQtdeParcCobertura(10, 5));*/
	}
	
	public static String removeExtension(String filename) {
	    if (filename == null) {
	        return null;
	    }
	    int index = indexOfExtension(filename);
	    if (index == -1) {
	        return filename;
	    } else {
	        return filename.substring(0, index);
	    }
	}
	
	public static int indexOfExtension(String filename) {
	    if (filename == null) {
	        return -1;
	    }
	    int extensionPos = filename.lastIndexOf('.');
	    int lastDirSeparator = filename.lastIndexOf('/');
	    if (lastDirSeparator > extensionPos) {
	        return -1;
	    }
	    return extensionPos;
	}
	
	@SuppressWarnings("unused")
	public static class MaxCobertura {
		private static final BigDecimal VR_MAX_COBERTURA = new BigDecimal(2000);
		private static final BigDecimal VR_MAX_PARC_COBERTURA = new BigDecimal(250);
		private static final Integer VR_MAX_QTDE_PARC_COBERTURA = new Integer(6);
	};
	
	private static BigDecimal calcularValorCobertura(
			BigDecimal vrCobertura,
			BigDecimal vrFinanciado,
			NumberUtils numberUtils) {
		
		if (vrCobertura != null && vrFinanciado != null) {
			if (!(MaxCobertura.VR_MAX_COBERTURA.compareTo(vrCobertura) > 0)) {
				vrCobertura = MaxCobertura.VR_MAX_COBERTURA;
			}
			if (vrCobertura.compareTo(vrFinanciado) > 0) {
				vrCobertura = vrFinanciado;
			}
			vrCobertura = numberUtils.setScale(vrCobertura, 2);
		}
		return vrCobertura;
	}

	private static BigDecimal calcularValorParcCobertura(
			BigDecimal vrParcCobertura,
			BigDecimal vrParcFinanciado,
			NumberUtils numberUtils) {
		
		if (vrParcCobertura != null && vrParcFinanciado != null) {
			if (!(MaxCobertura.VR_MAX_PARC_COBERTURA.compareTo(vrParcCobertura) > 0)) {
				vrParcCobertura = MaxCobertura.VR_MAX_PARC_COBERTURA;
			}
			if (vrParcCobertura.compareTo(vrParcFinanciado) > 0) {
				vrParcCobertura = vrParcFinanciado;
			}
			vrParcCobertura = numberUtils.setScale(vrParcCobertura, 2);
		}
		return vrParcCobertura;
	}
	
	private static Integer calcularQtdeParcCobertura(
			Integer qtdeParcCobertura,
			Integer qtdeParcFinanciado) {
		
		if (qtdeParcCobertura != null && qtdeParcFinanciado != null) {
			if (!(MaxCobertura.VR_MAX_QTDE_PARC_COBERTURA.compareTo(qtdeParcCobertura) > 0)) {
				qtdeParcCobertura = MaxCobertura.VR_MAX_QTDE_PARC_COBERTURA;
			}
			if (qtdeParcCobertura.compareTo(qtdeParcFinanciado) > 0) {
				qtdeParcCobertura = qtdeParcFinanciado;
			}
		}
		return qtdeParcCobertura;
	}
}
