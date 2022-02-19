  var   focus_width=280;   
  var   focus_height=218;  
  var   text_height=22;  
  var   swf_height   =   focus_height+text_height;   

  var   pics='image/1.jpg|image/2.jpg|image/3.jpg|image/4.jpg';   
  var links='picnews.do?id=1|picnews.do?id=2|picnews.do?id=3|picnews.do?id=4';   
  var   texts='传承文明建设共创辉煌|励志精神发扬奋发向上|学校技能设备教室配置|迎新晚会尽在无限精彩';   
  function playit()
  { 

  document.write('<object   classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"  codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0"   width="'+   focus_width   +'"   height="'+   swf_height   +'">');   
  document.write('<param   name="allowScriptAccess"   value="sameDomain"><param   name="movie"   value="pixviewer.swf"><param   name="quality"   value="high"><param   name="bgcolor"   value="#ffffff">');   
  document.write('<param   name="menu"   value="false"><param   name=wmode   value="opaque">');   
  document.write('<param   name="FlashVars"   value="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+focus_width+'&borderheight='+swf_height+'&textheight='+text_height+'">');   
  document.write('<embed   src="pixviewer.swf"   wmode="opaque"   FlashVars="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'"   menu="false"   bgcolor="#ffffff"   quality="high"   width="'+   focus_width   +'"   height="'+   swf_height   +'"   allowScriptAccess="sameDomain"   type="application/x-shockwave-flash"   pluginspage="http://www.macromedia.com/go/getflashplayer"   />');
  document.write('</object>');  
  }
  playit();