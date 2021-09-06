import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Wing{
	int row;
    int col;
    int[][] bookSeat;
    
    Wing(){
    	
    }
    Wing(int row, int col) {
        this.row = row;
        this.col = col;
        this.bookSeat = new int[row][col];
    }  
}

class BookSeat{
	
	int maxRow = 0, maxCol  = 0, totalPassenger = 0, currentPassenger = 1;
	
     void bookAisleSeats(List<Wing> wings) {
		System.out.println("Enter Asile Seats");
        int row = 0;
        while (row < maxRow && currentPassenger <= totalPassenger) {
            for(int i = 0; i < wings.size(); i++) {
            	Wing wing = wings.get(i);
            	System.out.println(wing);
            	if(wing.row > row) {
            		System.out.println(row);
            		if(i==0) {
            			System.out.println(currentPassenger);
            			wing.bookSeat[row][wing.col-1] = currentPassenger++;
            			System.out.println(wing.bookSeat[row][wing.col-1]);
            		}
            		else {
            			wing.bookSeat[row][0] =  currentPassenger++;
                        if(wing.col - 1 != 0) {
                        	
                            if(currentPassenger < totalPassenger)
                            {
                            	if(i != (wings.size()-1)) {
                                    wing.bookSeat[row][wing.col - 1] = currentPassenger++;
                                }
                                
                            }
                            
                        }
            		}
            		
            	}
            }
            row++;
        }
    }
	
	 void bookWindowSeats(List<Wing> wings) {
		int row = 0;
		while (row < maxRow && currentPassenger <= totalPassenger) {
		    Wing wing = wings.get(0);
		    if(wing.row > row ) {
		        wing.bookSeat[row][0] =  currentPassenger++;
		    }
		    
		    if(currentPassenger < totalPassenger) {
		    	Wing lWing = wings.get(wings.size() - 1);
			    if(lWing.row > row) {
			    	lWing.bookSeat[row][lWing.col - 1] =  currentPassenger++;
			    }
		    }
		    
		    row++;
		}
    }
	
	 void bookMidSeats(List<Wing> wings) {
        int row = 0;
        while (row < maxRow && currentPassenger <= totalPassenger) {
            for(int i = 0; i < wings.size(); i++) {
                Wing section = wings.get(i);
                if(section.row > row && section.col > 2) {
                	
                		for(int j = 1; j < section.col - 1; j++) {
                			if(currentPassenger < totalPassenger) {
                				section.bookSeat[row][j] =  currentPassenger++;
                			}
                            
                        }
                }
            }
            row++;
        }
    }
	
}

public class Airplane {
	
	public static void main(String argd[]) {
		
		Wing wing = new Wing();
		List<Wing> wings = new ArrayList<>();
		BookSeat bs = new BookSeat();
		System.out.println("Welcome to Airplane Ticket Booking");
		Scanner sc = new Scanner(System.in);
		int[][] seat = new int[4][2];
		int totalPassenger = 0;
		int row = 0;
		int col = 0;
			
		for(int i=0;i<4;i++) {
			System.out.println("Please Enter the number of rows and columns for Wing - "+ (i+1));
			for(int j=0;j<2;j++) {
				seat[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0;i<4;i++) {
			System.out.println("Wing - "+(i+1));
			for(int j=0;j<2;j++) {
				if( j == 0)
					col =seat[i][j];
				else
					row = seat[i][j];
			}
			
			wings.add(new Wing(row,col));
		}
				
		wings.stream().forEach((w) -> {
			if(w.row > bs.maxRow) {
				bs.maxRow = w.row;
			}
			
			if(w.col > bs.maxCol) {
				bs.maxCol = w.col;
			}
			
		});

		System.out.println(bs.maxRow + " " + bs.maxCol);
		
		System.out.println("Please Enter Total Number of Passengers");
		totalPassenger = sc.nextInt();
		
		bs.totalPassenger = totalPassenger;
		
		bs.bookAisleSeats(wings);
		bs.bookWindowSeats(wings);
		bs.bookMidSeats(wings);
		
		
		for (int i = 0; i < wings.size(); i++) {
            System.out.println("Wing : "+ (i+1));
            
            for(int m = 0; m < wings.get(i).row; m++) {
                for (int n = 0; n < wings.get(i).col; n++) {
                    System.out.print(wings.get(i).bookSeat[m][n] + " ");
                }
             System.out.println();
                
            System.out.println();
        }
		
	}
		
	}

}

