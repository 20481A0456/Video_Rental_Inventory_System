import java.util.Scanner;
class Video
{
	String videoName;
	boolean checkout;
	int rating;
	Video(String name)
	{
		videoName=name;
	}
	String getName()
	{
		return videoName;
	}
	void doCheckout()
	{
		checkout=true;
	}
	void doReturn()
	{
		checkout=false;
	}
	void receiveRating(int rating)
	{
		this.rating=rating;
	}
	int getRating()
	{
		return rating;
	}
	boolean getCheckout()
	{
		return checkout;
	}
}
class VideoStore
{
	static int count=0;
	Video[] store= new Video[100];
	void addVideo(String name)
	{
		boolean b=true;
		for(int i=0;i<100;i++)
		{
			if(store[i]==null)
			{
				break;
			}
			else if (store[i].getName().equals(name))
			{
				System.out.format("Video \"%s\" already exist.",name);
				b=false;
				break;
			}
		}
		if(b)
		{
			store[count]= new Video(name);
			count++;
			System.out.format("Video \"%s\" added successfully.",name);
		}
	}
	void doCheckout(String name)
	{
		boolean b=false;
		for (int i=0;i<100;i++)
		{
			if(store[i]==null)
			{
				break;
			}
			else if (store[i].getName().equals(name))
			{
				if(store[i].getCheckout()==true)
				{
					System.out.format("Video \"%s\" has already checked.",name);
					b=true;
					break;
				}
				else
				{
					System.out.format("Video \"%s\" checked out successfully.",name);
					b=true;
					store[i].doCheckout(); 
					break;
				}
			}
		}
		if (b==false)
		{
			System.out.format("Video \"%s\" does not exist.",name);
		}
	}
	void doReturn(String name)
	{
		for (int i=0;i<100;i++)
		{
			if(store[i]==null)
			{
				break;
			}
			else if(store[i].getName().equals(name))
			{
				if(store[i].getCheckout()==false)
				{
					System.out.format("Video \"%s\" has already returned.",name);
					break;
				}
				else
				{
					store[i].doReturn();
					System.out.format("Video \"%s\" returned successfully.",name);
					break;
				}			
			}
		}		
	}
	void receiveRating(String name, int rating)
	{
		boolean b=true;
		for (int i=0;i<100;i++)
		{
			if(store[i]==null)
			{
				break;
			}
			else if(store[i].getName().equals(name))
			{
				store[i].receiveRating(rating);
				System.out.format("Rating \"%d\" has been mapped to the Video \"%s\".",rating,name);
				b=false;
			}
		}
		if(b)
		{
			System.out.format("The Video \"%s\" does not exist.",name);	
		}
	}
	void listinventory()
	{
		System.out.println("-------------------------------------------------------------");
		System.out.println("Video name\t\tCheckout Status\t\tRating");
		for (int i=0;i<100;i++)
		{
			if(store[i]==null)
			{
				break;
			}
			else 
			{
				System.out.println(store[i].getName()+"\t\t\t"+store[i].getCheckout()+"\t\t\t"+store[i].getRating());
			}
		}
		System.out.println("-------------------------------------------------------------");		
	}
}
class VideoLauncher 
{
	public static void main(String[] args) 
	{
		boolean b=true;
		VideoStore vs=new VideoStore();
		Scanner s=new Scanner(System.in);
		while(b)
		{
			System.out.println();
			System.out.println();
			System.out.println("MAIN MENUE");
			System.out.println("==========");
			System.out.println("1.Add Videos: ");
			System.out.println("2.Check Out Video: ");
			System.out.println("3.Return Video: ");
			System.out.println("4.Receive Rating: ");
			System.out.println("5.List Inventory: ");
			System.out.println("6.Exit: ");
			System.out.print("Enter your choice (1..6): ");
			int choice=s.nextInt();
			if(choice==1)
			{
				System.out.print("Enter the name of the video you want to add: ");
				s.skip("\r\n");
				String vidName1=s.nextLine();
				vs.addVideo(vidName1);
			}
			else if(choice==2)
			{
				System.out.print("Enter the name of the video you want to check out: ");
				s.skip("\r\n");
				String vidName2=s.nextLine();
				vs.doCheckout(vidName2);
			}
			else if(choice==3)
			{
				System.out.print("Enter the name of the video you want to Return: ");
				s.skip("\r\n");
				String vidName3=s.nextLine();
				vs.doReturn(vidName3);
			}
			else if(choice==4)
			{
				System.out.print("Enter the name of the video you want to Rate: ");
				s.skip("\r\n");
				String vidName4=s.nextLine();
				System.out.print("Enter the rating for this video: ");
				int rate=s.nextInt();
				while(rate>10)
				{
					System.out.println("The rating should be in between 1 to 10");
					System.out.print("Enter the rating for this video again!! : ");
					rate=s.nextInt();
				}
				vs.receiveRating(vidName4,rate);
			}
			else if(choice==5)
			{
				vs.listinventory();
			}
			else if(choice==6)
			{
				System.out.println("Exiting...!! Thanks for using this application");
				b=false;
			}
			else 
			{
				System.out.println("choice must be 1 to 6 (digit)");
			}
		}
	}
}