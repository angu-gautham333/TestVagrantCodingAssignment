package files;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.payLoad;
import io.restassured.path.json.JsonPath;

public class testScripts {
	
	JsonPath js = new JsonPath(payLoad.rcbTeamDetails());
	int playersCount = js.getInt("player.size()");	
	
	//1. Write a test that validates that the team has only 4 foreign players
	@Test
	public void validateForeignPlayersCount() {		
		
		
		int foreignPlayersCount = 0;
		for(int i=0;i<playersCount;i++) {
			String country = js.getString("player["+i+"].country");
			if(!country.equalsIgnoreCase("India")) {
				foreignPlayersCount+=1;
			}
		}
		Assert.assertEquals(true, foreignPlayersCount==4, "The number of Foreign Players in the team is not 4. Scrpit Failed.");			
	}
	
	//2. Write a test that validates that there is at least one wicket keeper
	@Test
	public void validateOneWicketKeeper() {
		int wicketKeeperCount=0;
		for(int i=0;i<playersCount;i++) {
			String role = js.getString("player["+i+"].role");
			if(role.equalsIgnoreCase("Wicket-keeper")) {
				wicketKeeperCount+=1;
			}
		}
		Assert.assertEquals(true,wicketKeeperCount>=1,"The team has no Wicket Keepers. Script Failed.");		
	}

}
