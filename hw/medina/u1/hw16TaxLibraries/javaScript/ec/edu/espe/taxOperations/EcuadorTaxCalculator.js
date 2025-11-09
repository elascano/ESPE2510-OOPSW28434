export default class EcuadorTaxCalculator {

  static calculateTaxableBase(grossAnnualIncome, allowableDeductions) {
    return grossAnnualIncome - allowableDeductions;
  }

  static findTaxBracket(taxBase) {
    if (taxBase > 0 && taxBase < 12081) {
      return "Bracket 1: Base = 0, Excess up to $12,081 your Basic Fraction Tax is $0 and you're free of taxes";
    } else if (taxBase >= 12081 && taxBase < 15387) {
      return "Bracket 2: Base = $12,081, Excess up to $15,387. Your Basic Fraction Tax is $0 and your excess fraction tax rate is 5%.";
    } else if (taxBase >= 15387 && taxBase < 19978) {
      return "Bracket 3: Base = $15,387, Excess up to $19,978. Your Basic Fraction Tax is $165 and your excess fraction tax rate is 10%.";
    } else if (taxBase >= 19978 && taxBase < 26422) {
      return "Bracket 4: Base = $19,978, Excess up to $26,422. Your Basic Fraction Tax is $624 and your excess fraction tax rate is 12%.";
    } else if (taxBase >= 26422 && taxBase < 34770) {
      return "Bracket 5: Base = $26,422, Excess up to $34,770. Your Basic Fraction Tax is $1,398 and your excess fraction tax rate is 15%.";
    } else if (taxBase >= 34770 && taxBase < 46089) {
      return "Bracket 6: Base = $34,770, Excess up to $46,089. Your Basic Fraction Tax is $2,650 and your excess fraction tax rate is 20%.";
    } else if (taxBase >= 46089 && taxBase < 61359) {
      return "Bracket 7: Base = $46,089, Excess up to $61,359. Your Basic Fraction Tax is $4,914 and your excess fraction tax rate is 25%.";
    } else if (taxBase >= 61359 && taxBase < 81817) {
      return "Bracket 8: Base = $61,359, Excess up to $81,817. Your Basic Fraction Tax is $8,731 and your excess fraction tax rate is 30%.";
    } else if (taxBase >= 81817 && taxBase < 108810) {
      return "Bracket 9: Base = $81,817, Excess up to $108,810. Your Basic Fraction Tax is $14,869 and your excess fraction tax rate is 35%.";
    } else if (taxBase >= 108810) {
      return "Excess over $108,810. Your Basic Fraction Tax is $24,316 and your excess fraction tax rate is 37%.";
    }
    return "Tax base not within valid range";
  }

  static calculateTotalTax(taxBase) {
    let taxableSurplus, taxOnSurplus, totalTax;

    if (taxBase > 0 && taxBase < 12081) {
      return 0.0;
    } else if (taxBase >= 12081 && taxBase < 15387) {
      taxableSurplus = taxBase - 12081;
      taxOnSurplus = taxableSurplus * 0.05;
      totalTax = taxOnSurplus + 0;
      return totalTax;
    } else if (taxBase >= 15387 && taxBase < 19978) {
      taxableSurplus = taxBase - 15387;
      taxOnSurplus = taxableSurplus * 0.10;
      totalTax = taxOnSurplus + 165;
      return totalTax;
    } else if (taxBase >= 19978 && taxBase < 26422) {
      taxableSurplus = taxBase - 19978;
      taxOnSurplus = taxableSurplus * 0.12;
      totalTax = taxOnSurplus + 624;
      return totalTax;
    } else if (taxBase >= 26422 && taxBase < 34770) {
      taxableSurplus = taxBase - 26422;
      taxOnSurplus = taxableSurplus * 0.15;
      totalTax = taxOnSurplus + 1398;
      return totalTax;
    } else if (taxBase >= 34770 && taxBase < 46089) {
      taxableSurplus = taxBase - 34770;
      taxOnSurplus = taxableSurplus * 0.20;
      totalTax = taxOnSurplus + 2650;
      return totalTax;
    } else if (taxBase >= 46089 && taxBase < 61359) {
      taxableSurplus = taxBase - 46089;
      taxOnSurplus = taxableSurplus * 0.25;
      totalTax = taxOnSurplus + 4914;
      return totalTax;
    } else if (taxBase >= 61359 && taxBase < 81817) {
      taxableSurplus = taxBase - 61359;
      taxOnSurplus = taxableSurplus * 0.30;
      totalTax = taxOnSurplus + 8731;
      return totalTax;
    } else if (taxBase >= 81817 && taxBase < 108810) {
      taxableSurplus = taxBase - 81817;
      taxOnSurplus = taxableSurplus * 0.35;
      totalTax = taxOnSurplus + 14869;
      return totalTax;
    } else if (taxBase >= 108810) {
      taxableSurplus = taxBase - 108810;
      taxOnSurplus = taxableSurplus * 0.37;
      totalTax = taxOnSurplus + 24316;
      return totalTax;
    }
    return 0;
  }
}
