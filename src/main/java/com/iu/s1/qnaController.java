package com.iu.s1;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iu.qna.QnaDTO;

@Controller

@RequestMapping(value="/qna/*")
public class qnaController {
	/*void이면 value값으로 경로가 간다*/
	@RequestMapping(value="/qnaWrite",method=RequestMethod.GET)
	public void write(){}
	
	
	/*
	 * 방법1
	 * @RequestMapping(value="/qnaWrite",method=RequestMethod.POST)
	public String write(HttpServletRequest request){
		String title=request.getParameter("title");
		int age=Integer.parseInt(request.getParameter("age"));
		System.out.println(title);
		System.out.println(age);
		
		return "qna/qnaList";
		
	}*/
	
	/*@RequestMapping(value="/qnaWrite",method=RequestMethod.POST)
	public String write(@RequestParam(value="title") String subject,String writer,int age, String[] name){
		QnaDTO qnaDTO = new QnaDTO();
		qnaDTO.setTitle(subject);
		qnaDTO.setWriter(writer);
		qnaDTO.setAge(age);
		qnaDTO.setName(name);
		
		for(String s : qnaDTO.getName()){
			System.out.println(s);
			}
		
		System.out.println(qnaDTO.getTitle());
		System.out.println(qnaDTO.getWriter());
		System.out.println(qnaDTO.getAge());
		
		return "qna/qnaList";
		
	}*/
	
	@RequestMapping(value="/qnaWrite",method=RequestMethod.POST)
	public String write(QnaDTO qnaDTO,String title){
		
		for(String s : qnaDTO.getName()){
			System.out.println(s);
			}
		
		System.out.println(qnaDTO.getTitle());
		System.out.println("title:"+title);
		System.out.println(qnaDTO.getWriter());
		System.out.println(qnaDTO.getAge());
		
		return "redirect:./qnaList?curPage=5";//아래 mapping으로 넘어간것이다.
		
	}
	
	
	@RequestMapping(value="qnaList", method={ RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView list(ModelAndView mv,@RequestParam(defaultValue="0",required=false)int curPage){
		QnaDTO qnaDTO = new QnaDTO();
		qnaDTO.setTitle("test");
		qnaDTO.setWriter("choa");
		qnaDTO.setAge(29);
		mv.addObject("view", qnaDTO);
		mv.setViewName("qna/qnaList");
		System.out.println(curPage);
		return mv;
	}
	
	@RequestMapping(value="/qnaView")
	public String view(HttpServletRequest request, Model model)
	{
		QnaDTO qnaDTO = new QnaDTO();
		qnaDTO.setTitle("test");
		qnaDTO.setWriter("choa");
		qnaDTO.setAge(29);
		model.addAttribute("view",qnaDTO)//서버쪽으로 보내는 전달 객체
			 .addAttribute("board","qna");
		//request.setAttribute("view", qnaDTO);
		return "qna/qnaView";
		
	}
	
	
}
