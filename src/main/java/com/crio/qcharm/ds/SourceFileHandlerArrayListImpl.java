package com.crio.qcharm.ds;

import com.crio.qcharm.request.EditRequest;
import com.crio.qcharm.request.PageRequest;
import com.crio.qcharm.request.SearchReplaceRequest;
import com.crio.qcharm.request.SearchRequest;
import com.crio.qcharm.request.UndoRequest;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class SourceFileHandlerArrayListImpl implements SourceFileHandler {


  public SourceFileHandlerArrayListImpl(String fileName) {
  }






  @Override
  public Page loadFile(FileInfo fileInfo) {
  }


  @Override
  public Page getPrevLines(PageRequest pageRequest) {
  }


  @Override
  public Page getNextLines(PageRequest pageRequest) {
  }


  @Override
  public Page getLinesFrom(PageRequest pageRequest) {
  }


  @Override
  public List<Cursor> search(SearchRequest searchRequest) {
  }


  @Override
  public void setCopyBuffer(CopyBuffer copyBuffer)
  {
  }


  @Override
  public CopyBuffer getCopyBuffer() {
  }


  @Override
  public SourceFileVersion cloneObj(SourceFileVersion ver) {
  }



  @Override
  public void editLines(EditRequest editRequest) {
  }


  // TODO: CRIO_TASK_MODULE_SEARCH_REPLACE
  // Input:
  //      SearchReplaceRequest
  //        1. pattern  - pattern to be found
  //        2. newPattern - pattern to be replaced with
  //        3. fileName
  // Description:
  //      using the SourceFileVersionArrayListImpl object find every occurrence of pattern
  //      and replace it with the given newPattern

  @Override
  public void searchReplace(SearchReplaceRequest searchReplaceRequest) {
  }







}
