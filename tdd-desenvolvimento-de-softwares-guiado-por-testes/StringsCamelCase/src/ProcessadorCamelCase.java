import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessadorCamelCase {

	public static List<String> converterCamelCase(String original) {
		validaCadeia(original);
		
		List<String> palavrasSeparadas = separaAsPalavrasIniciadasEmMaiusculas(original);
		palavrasSeparadas = separaAsSiglas(palavrasSeparadas);
		palavrasSeparadas = separaOsNumeros(palavrasSeparadas);
		
		return minimizaAsPalavras(palavrasSeparadas);
	}
	
	public static void validaCadeia(String cadeia){
		isCadeiaCaracteresVazia(cadeia);
		isCadeiaComecaComNumero(cadeia);
		isCadeiaContemAlgumCaracterEspecial(cadeia);
	}
	
	private static List<String> minimizaAsPalavras(List<String> palavras){
		List<String> minimizado = new ArrayList<>();
		
		for (String s : palavras) {
			if(isStringTodaMaiuscula(s)){
				minimizado.add(s);
			}else{
				minimizado.add(s.toLowerCase());
			}
		}
		
		return minimizado;
	}
	
	private static boolean isStringTodaMaiuscula(String s) {
		return s.equals(s.toUpperCase());
	}

	private static List<String> separaAsPalavrasIniciadasEmMaiusculas(String cadeia){
		String regex = "(?=[A-Z][a-z])";
		String[] split = cadeia.split(regex);
		
		return Arrays.asList(split);
	}
	
	private static List<String> separaAsSiglas(List<String> palavras){
		List<String> palavrasESiglas = new ArrayList<>();
		
		for (String s : palavras) {			
			String regex = "(?=([A-Z]{2,}[A-Z]+))";
			String[] split = s.split(regex);
			
			palavrasESiglas.addAll(Arrays.asList(split));
		}
		
		return palavrasESiglas;
	}
	
	private static List<String> separaOsNumeros(List<String> palavras){
		List<String> palavrasENumeros = new ArrayList<>();
		
		for (String s : palavras) {			
			String regex = "(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)";
			String[] split = s.split(regex);
			
			palavrasENumeros.addAll(Arrays.asList(split));
		}
		
		return palavrasENumeros;
	}
	
	private static void isCadeiaCaracteresVazia(String cadeiaCaracteres) {
		if(cadeiaCaracteres.length() == 0){
			throw new InvalidParameterException("A cadeia de caracteres não pode ser vazia.");
		}
	}
	
	private static void isCadeiaComecaComNumero(String cadeiaCaracteres){
		if(Character.isDigit(cadeiaCaracteres.charAt(0)))
			throw new InvalidParameterException("A cadeia de caracteres não pode começar com números.");
	}
	
	private static void isCadeiaContemAlgumCaracterEspecial(String cadeiaCaracteres){
		Pattern p = Pattern.compile("[#]");
		Matcher m = p.matcher(cadeiaCaracteres);
		
		if(m.find())
			throw new InvalidParameterException("A cadeia de caracteres não pode conter caracteres especiais.");
	}
	
}
