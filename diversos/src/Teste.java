import eu.dataaccess.footballpool.ArrayOftTeamInfo;
import eu.dataaccess.footballpool.Info;
import eu.dataaccess.footballpool.InfoSoapType;
import eu.dataaccess.footballpool.TGroupsCompetitors;
import eu.dataaccess.footballpool.TTeamInfo;
import eu.dataaccess.footballpool.TTournamentInfo;

public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Info info = new Info();

		InfoSoapType msg = info.getInfoSoap();

		TTournamentInfo i = msg.tournamentInfo();

		System.out.println(i.toString());

		for (TGroupsCompetitors g : msg.allGroupCompetitors().getTGroupsCompetitors()) {
			ArrayOftTeamInfo t =  g.getTeamsInGroup();
			for (TTeamInfo te : t.getTTeamInfo()) {
				System.out.println(te.getSName());
			}

		}

	}

}
