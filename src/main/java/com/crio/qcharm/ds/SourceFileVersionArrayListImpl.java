package com.crio.qcharm.ds;

import com.crio.qcharm.request.PageRequest;
import com.crio.qcharm.request.SearchRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SourceFileVersionArrayListImpl implements SourceFileVersion {
  String fileName;
  List<String> lines;

  public SourceFileVersionArrayListImpl(SourceFileVersionArrayListImpl obj) {
    
  }
 /* public SourceFileVersionArrayListImpl() {
    
  }*/









  // TODO: CRIO_TASK_MODULE_LOAD_FILE
  // Input:
  //     FileInfo - contains following information
  //         1. fileName
  //         2. List of lines
  // Steps:
  //     You task here is to construct SourceFileVersionArrayListImpl object by
  //     1. Storing the lines received from fileInfo object
  //     2. Storing the fileName received from fileInfo object.
  // Recommendations:
  //     1. Use Java ArrayList to store the lines received from fileInfo

  public SourceFileVersionArrayListImpl(FileInfo fileInfo) {
    this.fileName = fileInfo.fileName;
    this.lines = fileInfo.lines;
  }

  // TODO: CRIO_TASK_MODULE_LOAD_FILE
  // Input:
  //    1. lineNumber - The line number
  //    2. numberOfLines - Number of lines requested
  // Expected functionality:
  //    1. Get the requested number of lines starting before the given line number.
  //    2. Make page object of this and return.
  //    3. For cursor position use the value from pageRequest
  //    4. For fileName use the value from pageRequest
  // NOTE:
  //    If there less than requested number of lines, then return just those lines.
  //    Zero is the first line number of the file
  // Example:
  //    lineNumber - 50
  //    numberOfLines - 25
  //    Then lines returned is
  //    (line number 25, line number 26 ... , line number 48, line number49)


  @Override
  public Page getLinesBefore(PageRequest pageRequest) {
    int lineNumber = pageRequest.getStartingLineNo();
    int numberOfLines = pageRequest.getNumberOfLines();
    int startPointer = lineNumber-numberOfLines;
    int start = (startPointer >=0 ) ? (startPointer) : 0;
    List<String> retLines = this.lines.subList(start,lineNumber);
    Page page = new Page(retLines,start,fileName,pageRequest.getCursorAt());
    return page;
  }

  // TODO: CRIO_TASK_MODULE_LOAD_FILE
  // Input:
  //    1. lineNumber - The line number
  //    2. numberOfLines - Number of lines requested
  // Expected functionality:
  //    1. Get the requested number of lines starting after the given line number.
  //    2. Make page object of this and return.
  //    3. For cursor position use the value from pageRequest
  //    4. For fileName use the value from pageRequest
  // NOTE:
  //    If there less than requested number of lines, then return just those lines.
  //    Zero is the first line number of the file  @Override
  // Example:
  //    lineNumber - 50
  //    numberOfLines - 25
  //    Then lines returned is
  //    (line number 51, line number 52 ... , line number 74, line number75)

  @Override
  public Page getLinesAfter(PageRequest pageRequest) {
    int lineNumber = pageRequest.getStartingLineNo();
    int numberOfLines = pageRequest.getNumberOfLines();
    int endPointer = lineNumber + numberOfLines + 1; 
    int start = lineNumber+1;
    int end = (lines.size() >= (endPointer))?(endPointer):lines.size();
    List<String> retLines = new ArrayList<String>();
    if (start<lines.size()) {
      retLines = lines.subList(start,end);
    }
    else {
      start = lineNumber;
    }
    Cursor cursor = pageRequest.getCursorAt();
    Page page = new Page(retLines,start,fileName,cursor);
    return page;
  }

  // TODO: CRIO_TASK_MODULE_LOAD_FILE
  // Input:
  //    1. lineNumber - The line number
  //    2. numberOfLines - Number of lines requested
  // Expected functionality:
  //    1. Get the requested number of lines starting from the given line number.
  //    2. Make page object of this and return.
  //    3. For cursor position should be (startingLineNo, 0)
  //    4. For fileName use the value from pageRequest
  // NOTE:
  //    If there less than requested number of lines, then return just those lines.
  //    Zero is the first line number of the file  @Override
  // Example:
  //    lineNumber - 50
  //    numberOfLines - 25
  //    Then lines returned is
  //    (line number 50, line number 51 ... , line number 73, line number74)

  @Override
  public Page getLinesFrom(PageRequest pageRequest) {
    int lineNumber = pageRequest.getStartingLineNo();
    int numberOfLines = pageRequest.getNumberOfLines();
    int endPointer = lineNumber + numberOfLines; 
    int end = (lines.size() >= (endPointer))?(endPointer):lines.size();
    List<String> retLines = lines.subList(lineNumber,end);
    int startingLineNo = lineNumber;
    Cursor cursor = pageRequest.getCursorAt();
    Page page = new Page(retLines,lineNumber,fileName,cursor);
    return page;
  }






  @Override
  public List<String> getAllLines() {
    return lines;
  }

  @Override
  public List<Cursor> getCursors(SearchRequest searchRequest) {
    List<Cursor> cursorList = new ArrayList<Cursor> ();
    String pattern = searchRequest.getPattern();
    for (int i=0;i<lines.size();i++) {
      search(lines.get(i),pattern,i,0,cursorList);
    }
    return cursorList;
  }
 private void search(String line,String pattern,int lineNo,int startIndex,List<Cursor> cursorList){
  if(line.contains(pattern)) {
    if(line.startsWith(pattern)){
    int position = line.indexOf(pattern);
    Cursor cursor = new Cursor(lineNo,position+startIndex);
    cursorList.add(cursor);}
    //startIndex = position + pattern.length() + 1;
    startIndex = startIndex +1;
    String subString = line.substring(1);
    search(subString,pattern,lineNo,startIndex,cursorList);
  }
  
 }
 
 
}
