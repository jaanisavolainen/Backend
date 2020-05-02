package com.example.kyselyBack.web;


import java.util.ArrayList;
import java.util.List;

import org.json.*;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.kyselyBack.domain.Option;
import com.example.kyselyBack.domain.OptionRepository;
import com.example.kyselyBack.domain.Question;
import com.example.kyselyBack.domain.QuestionRepository;
import com.example.kyselyBack.domain.SurveyRepository;
import com.example.kyselyBack.domain.UserAnswer;
import com.example.kyselyBack.domain.UserAnswersRepository;

@Controller
@ResponseBody
public class QuestionController {
 
   
    @Autowired
    QuestionRepository qRepo;
   
    @Autowired
    OptionRepository oRepo;
    
    @Autowired
    UserAnswersRepository uaRepo;
    
    @Autowired
    SurveyRepository sRepo;
    
    
    
    
    
    @RequestMapping(value="/addQuestion", method = RequestMethod.POST)
    Question newQuestion(@RequestBody String jsonString) {
    	
    	Question q = new Question();
    	JSONObject obj = new JSONObject(jsonString);
    	
    	q.setQuestion(obj.getString("question"));
		q.setType(obj.getInt("type"));
		q.setSurvey(sRepo.findOneById(obj.getLong("surveyId")));
    	qRepo.save(q);
		
    	if (obj.getInt("type") != 3) {
    		JSONArray a = obj.getJSONArray("options");
    		for (int i = 0; i < a.length(); i++) {
    			oRepo.save(new Option(a.getJSONObject(i).getString("option"), qRepo.findOneByQuestion(obj.getString("question"))));
    		}
    	}
  	return q;
    }						
   
    @RequestMapping(value = "/getUserAnswers")
	List<UserAnswer> getUserAnswers() {	
		return (List<UserAnswer>) uaRepo.findAll();
	} 
    
    
    /*
     
    @RequestMapping(value="/questionsApi", method = RequestMethod.GET)
    public @ResponseBody List<Question> questionsRest() {
        return (List<Question>) qRepo.findAll();
    }
   
    @RequestMapping(value="/questionsApi/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Question> findQuestionRest(@PathVariable("id") Long id) {
        return qRepo.findById(id);
    }
   
    @GetMapping("/questions")
      List<Question> all() {
        return (List<Question>) qRepo.findAll();
      }
    
      @RequestMapping(value="/questions", method =RequestMethod.POST)
      Question newQuestion(@RequestBody Question newQuestion, Model model) {
    	model.addAttribute("question", new Question());
    	model.addAttribute("option", oRepo.findAll());
    	oRepo.save(newQuestion.getOption());
        return qRepo.save(newQuestion);
      }

      // Single item
 
      @GetMapping("/questions/{id}")
      Optional<Question> one(@PathVariable Long id) {
 
        return qRepo.findById(id);
      }
 
      @PutMapping("/questions/{id}")
      Question replaceQuestion(@RequestBody Question newQuestion, @PathVariable Long id) {
         
          return qRepo.findById(id)
                  .map(n -> {
                    n.setQuestion(newQuestion.getQuestion());
                    n.setOption(newQuestion.getOption());
                    n.setType(newQuestion.getType());
                    return qRepo.save(n);
                  })
                  .orElseGet(() -> {
                    newQuestion.setId(id);
                    return qRepo.save(newQuestion);
                  });
      }
 
      @DeleteMapping("/questions/{id}")
      void deleteQuestion(@PathVariable Long id) {
        qRepo.deleteById(id);
      }
      
      @GetMapping("/userAnswerses")
      List<UserAnswers> allUserAnswers() {
        return (List<UserAnswers>) uaRepo.findAll();
      }
      
      @GetMapping("/userAnswerses/{id}")
      Optional<UserAnswers> specificUserAnswer(@PathVariable Long id) {
 
        return uaRepo.findById(id);
      }
      
      @RequestMapping(value="/userAnswerses", method =RequestMethod.POST)
      UserAnswers newAnswer(@RequestBody UserAnswers newAnswer, Model model) {
    	model.addAttribute("userAnswers", new UserAnswers());
    	model.addAttribute("question", qRepo.findAll());
        return uaRepo.save(newAnswer);
      }
      
      @PutMapping("/userAnswerses/{id}")
      UserAnswers replaceAnswer(@RequestBody UserAnswers newAnswer, @PathVariable Long id) {
         
          return uaRepo.findById(id)
                  .map(n -> {
                    n.setTextAnswer(newAnswer.getTextAnswer());
                    n.setQuestion(newAnswer.getQuestion());
                    n.setType(newAnswer.getType());
                    return uaRepo.save(n);
                  })
                  .orElseGet(() -> {
                    newAnswer.setUserAnswerId(id);
                    return uaRepo.save(newAnswer);
                  });
      }
      
      @DeleteMapping("/userAnswerses/{id}")
      void deleteAnswer(@PathVariable Long id) {
        uaRepo.deleteById(id);
      }
      
      */
      
      
      
}
