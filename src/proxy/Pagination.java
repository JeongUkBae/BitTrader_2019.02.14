package proxy;

import javax.servlet.http.HttpServletRequest;

import lombok.Data;
import service.CustomerServiceImpl;

@Data
public class Pagination implements Proxy{
	private int pageNum, pageSize, blockSize, 
			startRow, endRow, startPage, endPage,
			prevBlock, nextBlock, totalcount;
	private boolean existPrev, existNext;

	
	@Override
	public void carryOut(Object o) {
		HttpServletRequest request = (HttpServletRequest)o;
		System.out.println("프록시 페이지네이션 도착");
		String _pageNum = request.getParameter("page_num");
		pageNum = (request.getParameter("page_num")==null)? 1: Integer.parseInt(_pageNum);
		
		String _pageSize = request.getParameter("page_size");
		pageSize = (request.getParameter("page_size")==null)? 5: Integer.parseInt(_pageSize);
		System.out.println("pageSize:::"+this.pageSize);
		
		
		
		totalcount = CustomerServiceImpl.getInstance().countCustomer(null);
		System.out.println("totalcount:::"+this.totalcount);
		
		//this.startRow = String.valueOf(totalcount-Integer.parseInt(this.pageSize));
		//this.endRow = String.valueOf(Integer.parseInt(this.startRow) +Integer.parseInt(this.pageSize)) ;  
		System.out.println("pageNUM??"+pageNum);
		System.out.println("pageSize??"+pageSize);
	
		/*
		 * startRow = (totalcount-((pageSize*pageNum)-1)); 
		 * endRow = ((totalcount-((pageSize*pageNum)-1))+4);
		 */
		blockSize = totalcount / pageSize; 
		System.out.println("count의 나누기는?"+blockSize);
		if(totalcount%pageSize != 0) {
			blockSize = blockSize+1;
		System.out.println("count의 나머지는?"+blockSize);
		}
		/*
		 * int ex = blockSize*pageSize; startRow = totalcount-(pageSize*pageNum)+1;
		 * endRow = startRow + pageSize-1; if(endRow<=0) { endRow = 0; }
		 * 
		 * System.out.println("1. end:: "+ex);
		 * System.out.println("=== startrow:: "+startRow);
		 * 
		 * System.out.println("=== row:: "+endRow);
		 * 
		 * System.out.println("startRow:::"+startRow);
		 * System.out.println("endrow:::"+endRow);
		 */
		
		
		System.out.println("전체 카운트: "+totalcount); 
		int pageCount = totalcount / pageSize; 
		System.out.println("전체 페이지수: "+pageCount); 
		startRow = (pageNum -1) *pageSize + 1; 
		System.out.println("스타트로우: "+startRow); 
		endRow = (totalcount > pageNum * pageSize)? pageNum * pageSize: pageCount;
		System.out.println("엔드로우: "+endRow);
		 
		System.out.println("blockSize::"+blockSize);
	//	blockSize
	
		endPage = totalcount/pageSize-1;
		startPage = endPage-pageSize+1;

		System.out.println("==startPage::"+startPage);
		System.out.println("==endPage::"+endPage);
		
	
		existPrev = (startPage - pageSize) > 0;
		existNext = (startPage + pageSize) <= pageCount;
		
		
		prevBlock = startPage - pageSize; 
		nextBlock = startPage + pageSize;
		System.out.println("==prevBlock::"+prevBlock);
		System.out.println("==nextBlock::"+nextBlock);
		
		
	
		
		
	}
	
}
