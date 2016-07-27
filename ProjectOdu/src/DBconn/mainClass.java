package DBconn;

import sist.co.pds.PdsDAO;

public class mainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*MemberDAO dao = MemberDAO.getInstance();
		dao.searchBBSList(1, "test");*/
		PdsDAO pdao = PdsDAO.getInstance();
		pdao.detailPDS(3);
	}

}
