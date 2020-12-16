package com.kh.soomc.board.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.soomc.board.model.domain.Board;
import com.kh.soomc.board.model.service.BoardReplyService;
import com.kh.soomc.board.model.service.BoardService;
import com.kh.soomc.test.MyTestComponent;

@Controller
public class BoardController {
	@Autowired
	private BoardService bService;

	//@Autowired
	//private MyTestComponent mytest01;

	@Autowired
	private BoardReplyService brService;

	public static final int LIMIT = 10;

	// 게시글 작성 페이지
	@RequestMapping(value = "/writerForm.do", method = RequestMethod.GET)
	public String boardInsertForm(ModelAndView mv) {
		// DI
//		//예전방식
//		MyTestComponent tf = new MyTestComponent();
//		tf.testFunc();
		// DI 방식
		//mytest01.testFunc();
		// 위에 autowired랑 mytestcomponent의 component 꼭 달아주기

		return "board/writeForm"; // View페이지에서 작성 후 form action = "bInsert.do" 로 들어오도록 함.
	}

	// 작성된 글을 insert
	@RequestMapping(value = "/bInsert.do", method = RequestMethod.POST)
	public ModelAndView boardInsert(Board b, @RequestParam(name = "upfile") MultipartFile report,
			HttpServletRequest request, ModelAndView mv) {

		// 첨부파일 저장
		if (report != null && !report.equals("")) {
			saveFile(report, request);
		}
		b.setBoard_file(report.getOriginalFilename()); // 저장된 파일명을 vo에 set

		bService.insertBoard(b);
		mv.setViewName("redirect:bList.do"); // insertBoard에 성공했다면 !!! View페이지로 이동하는 것이 아니라 컨트롤러 url 중 "게시글 리스트 select로
												// 이동" 하는 "/bList.do"
		return mv;
		// 실패했다면
//		mv.setViewName("errorPage");   // errorPage 페이지로 이동
	}

	// 게시글 리스트 select
	@RequestMapping(value = "/bList.do")
	public ModelAndView boardListService(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "keyword", required = false) String keyword, ModelAndView mv) {
//		mv.addObject("list", bService.selectList());
//		mv.setViewName("board/blist");    // board/blist View페이지가 보여짐
//		return mv;
		try {
			int currentPage = page;
			int listCount = bService.totlaCount();
			int maxPage = (int) ((double) listCount / LIMIT + 0.9);

			if (keyword != null && !keyword.equals(""))
				mv.addObject("list", bService.selectSearch(keyword));
			else
				mv.addObject("list", bService.selectList(currentPage, LIMIT));
			mv.addObject("currentPage", currentPage);
			mv.addObject("maxPage", maxPage);
			mv.addObject("listCount", listCount);
			mv.setViewName("board/boardList");
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("errorPage");
		}
		return mv;

	}

	// boardDetail 메소드 이름이 두개인데 이것은 다형성으로 가능한 것이다.
	// annotation에 걸려진 value를 타고 들어와서 그 다음 메소드를 실행시켜주기 때문에 메소드 이름이 같아도 상관없다.
	@RequestMapping(value = "bDetail.do", method = RequestMethod.GET)
	public ModelAndView boardDetail(@RequestParam(name = "board_num") String board_num,
			@RequestParam(name = "page", defaultValue = "1") int page, ModelAndView mv) {
		//System.out.println("board_num : " + board_num);
		try {
			int currentPage = page;
			mv.addObject("board", bService.selectBoard(0, board_num));
			mv.addObject("commentList", brService.selectList(board_num));
			mv.addObject("currentPage", currentPage);

			mv.setViewName("board/boardDetail");
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("errorPage");
		}
		return mv;
	}

	@RequestMapping(value = "bRenew.do", method = RequestMethod.GET)
	public ModelAndView boardDetail(@RequestParam(name = "board_num") String board_num, ModelAndView mv) {
		try {
			mv.addObject("board", bService.selectBoard(1, board_num));
			mv.setViewName("board/boardRenew");
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("errorPage");
		}
		return mv;
	}

	private void removeFile(String board_file, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\uploadFiles";
		String filePath = savePath + "/" + board_file;
		try {
			System.out.println(board_file + "을 삭제합니다.");
			System.out.println("기존 저장 경로 : " + savePath);
			File delFile = new File(filePath);
			delFile.delete();
			System.out.println("파일 삭제가 완료되었습니다.");
		} catch (Exception e) {
			System.out.println("파일 삭제 에러 : " + e.getMessage());
		}
	}

	private void saveFile(MultipartFile report, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\uploadFiles";
		File folder = new File(savePath);
		if (!folder.exists()) {
			folder.mkdir(); // 폴더가 없다면 생성한다.
		}
		String filePath = null;
		try {
			// 파일 저장
			System.out.println(report.getOriginalFilename() + "을 저장합니다.");
			System.out.println("저장 경로 : " + savePath);

			filePath = folder + "/" + report.getOriginalFilename();
			report.transferTo(new File(filePath)); // 파일을 저장한다
			System.out.println("파일 명 : " + report.getOriginalFilename());
			System.out.println("파일 경로 : " + filePath);
			System.out.println("파일 전송이 완료되었습니다.");
		} catch (Exception e) {
			System.out.println("파일 전송 에러 : " + e.getMessage());
		}
	}

	@RequestMapping(value = "bUpdate.do", method = RequestMethod.POST)
	public ModelAndView boardUpdate(Board b, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "upfile") MultipartFile report, HttpServletRequest request, ModelAndView mv) {
		try {
			if (report != null || !report.getOriginalFilename().equals("")) {
//			if (report != null && !report.equals("")) {
//				System.out.println(report.getName());   //  upfile
//				System.out.println(report.getOriginalFilename());   // 선택된 filename
				removeFile(b.getBoard_file(), request);
				saveFile(report, request);
				b.setBoard_file(report.getOriginalFilename());
			}
//				b.setBoard_file(report.getOriginalFilename());
			mv.addObject("board_num", bService.updateBoard(b).getBoard_num());
			mv.addObject("currentPage", page);
			mv.setViewName("redirect:bDetail.do");
		} catch (Exception e) {
			System.out.println("실패 transaction 실패");
			mv.addObject("msg", e.getMessage());
			mv.setViewName("errorPage");
		}
		return mv;
	}

	@RequestMapping(value = "bDelete.do", method = RequestMethod.GET)
	public ModelAndView boardDelete(@RequestParam(name = "board_num") String board_num,
			@RequestParam(name = "page", defaultValue = "1") int page, HttpServletRequest request, ModelAndView mv) {
		try {
			Board b = bService.selectBoard(1, board_num);
			removeFile(b.getBoard_file(), request);
			bService.deleteBoard(board_num);
			mv.addObject("currentPage", page);
			mv.setViewName("redirect:bList.do");

		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("errorPage");
		}
		return mv;
	}
}
