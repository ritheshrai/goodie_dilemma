import java.io.*;
import java.util.*;
public class solution1 {
  public static void main(String[] args) throws Exception {
    int numEmployee;
    ArrayList<Goodie> inputData = new ArrayList<Goodie>(); 
    Scanner sc=new Scanner(System.in);
    System.out.println("1.Manual input\n2.Use Input\n3.change employes_number");
    int j=sc.nextInt();
    //checing for manual input data or read from the input file
    if(j==1){
    System.out.println("Enter number of employes : ");
    numEmployee = sc.nextInt();
    System.out.println("Enter number of goodies : ");
    int i=sc.nextInt();
    sc.nextLine();//decoi for newline error 
    while(i>0){
      System.out.println("Enter goodie \"name : value\" formate ");
      String current[] = sc.nextLine().split(": ");
      inputData.add(new Goodie(current[0], Integer.parseInt(current[1])));
      i--;
     }
     sc.close(); } 
    else
    {    
          // exception for safty while reading filestream from the sample_input.txt
          FileInputStream inputFromFile=new FileInputStream("sample_input.txt");       
          Scanner s=new Scanner(inputFromFile);
          if(j==3){
            System.out.println("Enter number of employes : ");
            numEmployee = sc.nextInt();
            sc.close();
            s.nextLine();//skipping employee number from file
          }else{
          //parsing the integer from string of "Number of employees: 4"
          numEmployee = Integer.parseInt(s.nextLine().split(": ")[1]); //here [0]="Number of employees" and [1]="4"
          }
          // skipping 3 lines down for content in input file
          s.nextLine(); 
          s.nextLine(); 
          s.nextLine();
          // intializing class to hold input data from file
          while(s.hasNextLine())  
          {
            String current[] = s.nextLine().split(": ");
            inputData.add(new Goodie(current[0], Integer.parseInt(current[1])));
          }
          //termionate the Input_stream
          s.close();  
    }
    //sorting the data in goodies to compute min and max
    Collections.sort(inputData, new Comparator<Goodie>(){
      public int compare(Goodie a, Goodie b) { 
             return a.price - b.price; 
        } 
        });
    //main logic
    int diffMin = inputData.get(inputData.size()-1).price;
    int indexMin = 0;
    for(int k=0;k<inputData.size()-numEmployee+1;k++) {
      int diff = inputData.get(numEmployee+k-1).price-inputData.get(k).price;
        if(diff<=diffMin) {
        diffMin = diff;
        indexMin = k;
      }
    }
    
    
    //wirting to output file
    FileWriter Writer = new FileWriter("output.txt");
    Writer.write("The goodies selected for distribution are:\n\n");
    System.out.println("The goodies selected for distribution are:\n\n"); //formating for similar look as example
    for(int k=indexMin;k<indexMin + numEmployee; k++) {
      Writer.write(inputData.get(k).toString() + "\n");
      System.out.println(inputData.get(k).toString() + "\n");
    }

    Writer.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + diffMin);
    System.out.println("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + diffMin);
    Writer.close(); 
    System.out.println("output in recorded in a output.txt file");
  }

}
//class for goodies
 class Goodie {
    String name;
    int price;
    //constructor
    public Goodie(String name, int price) {
      this.name = name;
      this.price = price;
    }
    //printing style
    public String toString() { 
        return this.name + ": " + this.price;
    }
  }