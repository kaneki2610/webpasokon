package com.example.dell.myapplication14.Presenter.Evaluate;

import com.example.dell.myapplication14.Model.Evaluate.EvaluateModel;
import com.example.dell.myapplication14.Model.Object.Evaluate;
import com.example.dell.myapplication14.View.Evaluate.EvaluateViewImp;


public class EvaluatePresenterLogic implements EvaluatePresenterImp {
    EvaluateViewImp evaluateViewImp;
    EvaluateModel evaluateModel;

    public EvaluatePresenterLogic(EvaluateViewImp e) {
        this.evaluateViewImp = e;
        evaluateModel = new EvaluateModel();
    }

    @Override
    public void addEvaluation(Evaluate evaluate) {
        boolean result = evaluateModel.AddEvaluate(evaluate);
        if(result)
        {
            evaluateViewImp.SuccessEvaluate();
        }else{
            evaluateViewImp.FailEvaluate();
        }
    }
}
