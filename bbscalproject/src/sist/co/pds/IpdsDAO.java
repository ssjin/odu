package sist.co.pds;

import java.util.List;

public interface IpdsDAO {
	
	List<pdsDTO> getPDSList();
	boolean writePDS(pdsDTO pds);
	boolean downloadCount(int pdsid);
	pdsDTO getPDS(int seq);
	void readCount(int seq);
	boolean uploadPDS(pdsDTO pds);
	boolean deletePDS(int seq);

}
