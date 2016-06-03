import java.time.LocalDate;
import java.time.Month;

public enum Signo {
	
	ARIES(LocalDate.of(2016, Month.MARCH, 21).getDayOfYear(), LocalDate.of(2016, Month.APRIL, 20).getDayOfYear()),
	TOURO(LocalDate.of(2016, Month.APRIL, 21).getDayOfYear(), LocalDate.of(2016, Month.MAY, 20).getDayOfYear()),
	GEMEOS(LocalDate.of(2016, Month.MAY, 21).getDayOfYear(), LocalDate.of(2016, Month.JUNE, 20).getDayOfYear()),
	CANCER(LocalDate.of(2016, Month.JUNE, 21).getDayOfYear(), LocalDate.of(2016, Month.JULY, 21).getDayOfYear()),
	LEAO(LocalDate.of(2016, Month.JULY, 22).getDayOfYear(), LocalDate.of(2016, Month.AUGUST, 22).getDayOfYear()),
	VIRGEM(LocalDate.of(2016, Month.AUGUST, 23).getDayOfYear(), LocalDate.of(2016, Month.SEPTEMBER, 22).getDayOfYear()),
	LIBRA(LocalDate.of(2016, Month.SEPTEMBER, 23).getDayOfYear(), LocalDate.of(2016, Month.OCTOBER, 22).getDayOfYear()),
	ESCORPIAO(LocalDate.of(2016, Month.OCTOBER, 23).getDayOfYear(), LocalDate.of(2016, Month.NOVEMBER, 21).getDayOfYear()),
	SAGITARIO(LocalDate.of(2016, Month.NOVEMBER, 22).getDayOfYear(), LocalDate.of(2016, Month.DECEMBER, 21).getDayOfYear()),
	CAPRICORNIO(LocalDate.of(2016, Month.DECEMBER, 22).getDayOfYear(), LocalDate.of(2016, Month.JANUARY, 20).getDayOfYear()),
	AQUARIO(LocalDate.of(2016, Month.JANUARY, 21).getDayOfYear(), LocalDate.of(2016, Month.FEBRUARY, 19).getDayOfYear()),
	PEIXES(LocalDate.of(2016, Month.FEBRUARY, 20).getDayOfYear(), LocalDate.of(2016, Month.MARCH, 20).getDayOfYear());
	
	private int dataInicio;
	private int dataFim;

	/**
	 * Os parametros dataInicio e dataFim guardam o dia do ano (1/365 ou 366)
	 * @param dataInicio
	 * @param dataFim
	 */
	private Signo(int dataInicio, int dataFim) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}
	
	/**
	 * Compara se a dataNascimeto esta entre a dataInicio e dataFim de um signo
	 * @param dataNascimento
	 * @return
	 */
	public boolean isDesteSigno(LocalDate dataNascimento){
		return dataNascimento.getDayOfYear() >= this.dataInicio && dataNascimento.getDayOfYear() <= this.dataFim;
	}
}
