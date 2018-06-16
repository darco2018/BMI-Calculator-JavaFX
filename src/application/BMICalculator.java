package application;


public final class BMICalculator {
	
	private double weightInKilograms;
	private double heightInCentimeters;
    enum Gender {MALE, FEMALE};
    private Gender gender;
   
	public BMICalculator(double weightInKilograms, double heightInCentimeters, Gender gender){
    	if(weightInKilograms <= 0 || weightInKilograms > 300)
    		throw new IllegalArgumentException("The weight must be greater than 0 kilograms and less than 300 kilograms");
    	
    	if(heightInCentimeters < 120 || heightInCentimeters > 240)
    		throw new IllegalArgumentException("The height can't be less than 120 centimeteres and less than 240 centimeteres");
    	
        this.weightInKilograms = weightInKilograms;        
        this.heightInCentimeters = heightInCentimeters; 
        this.gender = gender;
    }
    
    public void setWeightInKilograms(double weightInKilograms) {
    	
    	if(weightInKilograms <= 0 || weightInKilograms > 300)
    		throw new IllegalArgumentException("The weight must be greater than 0 kilograms and less than 300 kilograms");
    	    	
		this.weightInKilograms = weightInKilograms;
	}

	public void setHeightInCentimeters(double heightInCentimeters) {
		
		if(heightInCentimeters < 120 || heightInCentimeters > 240)
    		throw new IllegalArgumentException("The height can't be less than 120 centimeteres and less than 240 centimeteres");
    	
		this.heightInCentimeters = heightInCentimeters;
	}

	public void setGender(Gender gender) { this.gender = gender; }
	
	public double getWeightInKilograms() { return weightInKilograms; }

	public double getHeightInCentimeters() { return heightInCentimeters; }
	
	public Gender getGender() { return gender; }
	
	public String bmiTableForAdults() {
		
		int num1 = (gender == Gender.FEMALE) ? 20 : 19;
		int num2 = (gender == Gender.FEMALE) ? 25 : 24;
		
		return String.format("%s%n%s%n%s%n%s%n%s", 
				"Underweight < " + num1, 
	    		"Normal weight " + num1 + "-" + num2,
	    		"Overweight " + num2 + "-30",
	    		"Obese Class I and II 30-40",
	    		"Obese Class II > 40");
	}
    
    public double calculateBMI(){
    	
    	return getWeightInKilograms() /  
      			 (Math.pow(getHeightInCentimeters(), 2) / 10000); //divide by 10000 to get meters
    }
    
    public static String bmiInterpretation(double bmi, Gender sex)
    {
        if(bmi < 0)
        	return "BMI must be more than 0 to be interpreted!";
        
        int num1 = (sex == Gender.FEMALE) ? 20 : 19;
		int num2 = (sex == Gender.FEMALE) ? 25 : 24;
        
        if(bmi > 0 && bmi < num1)         
            return "Underweight!";
        else if(bmi >= num1 && bmi <= num2)
        	return "Normal weight!";
        else if(bmi > num2 && bmi < 30)
        	return "Overweight!";
        else
        	return "Obese!";
    }
}
