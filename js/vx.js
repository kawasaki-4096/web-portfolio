/** http://code.google.com/p/vxjs/ */
var _=_?_:{}
_.addclass=_.AC=function(e,c){if(_.HC(e))e.className+=' '+c}
_.ajax=_.X=function(u,f,d,x){x=new(window.ActiveXObject||XMLHttpRequest)('Microsoft.XMLHTTP')
x.open(d?'POST':'GET',u,1);x.setRequestHeader('Content-type','application/x-www-form-urlencoded');x.onreadystatechange=function(){x.readyState>3&&f&&f(x.responseText,x)};x.send(d)}
_.fx=_.A=function(v,n,c,f,u,y){u=0;(y=function(){u++<v&&c(u/v)!==0?setTimeout(y,n):(f?f():0)})()}
_.cls=_.C=function(n,d,y,k,h){y=(d?d:_.d).getElementsByTagName("*");h=[];for(k=y.length;k--;)
_.I(n,y[k].className.split(" "))>0&&h.push(y[k]);return h}
_.bind=_.B=function(f,s){return function(){return f.apply(s,_.Y(arguments))}}
_.array=_.Y=function(a,b){for(b=a.length,c=[];b--;)c.push(a[b]);return c}
_.on=_.E=function(e,t,f,r){if(e.attachEvent?(r?e.detachEvent('on'+t,e[t+f]):1):(r?e.removeEventListener(t,f,0):e.addEventListener(t,f,0))){e['e'+t+f]=f;e[t+f]=function(){e['e'+t+f](window.event)};e.attachEvent('on'+t,e[t+f])}}
_.fade=_.F=function(d,h,e,f,i){d=d=='in';_.A(f?f:15,i?i:50,function(a){if(h.stop)return 0;a=(d?0:1)+(d?1:-1)*a;h.style.opacity=a;h.style.filter='alpha(opacity='+100*a+')'})}
_.hasclass=_.HC=function(e,c){return _.I(c,e.className.split(" "))>0}
_.entity=_.H=function(s,d,t){t=_.d.createElement('textarea');t.innerHTML=s;return d?t.value:t.innerHTML}
_.index=_.I=function(v,a,i){for(i=a.length;i--&&a[i]!=v;);return i<0?i:i+1;}
_.pos=_.P=function(e,a){a={l:0,t:0,w:e.offsetWidth,h:e.offsetHeight};do{a.l+=e.offsetLeft;a.t+=e.offsetTop}while(e=e.offsetParent)return a}
_.query=_.Q=function(j,y,x){y='';for(x in j)y+='&'+x+'='+encodeURIComponent(j[x]);return y.slice(1)}
_.queue=_.U=function(l,n){(n=function(){eval(l.splice(0,1)[0])})();return l}
_.ready=_.R=function(f){"\v"=="v"?setTimeout(f,0):_.E(_.d,'DOMContentLoaded',f)}
_.id=_.G=function(e){return e.style?e:_.d.getElementById(e)}
_.d=document
_.slideH=function(d,e,o,f,i,q){q=_.P(e).h;_.A(f?f:15,i?i:10,function(a){a=(d?0:1)+(d?1:-1)*a;e.style.height=(a*q)+'px'},o)}
_.slide=function(d,e,o,f,i,q,w){p=_.P(e);q=p.h;w=p.w;_.A(f?f:15,i?i:10,function(a){a=(d?0:1)+(d?1:-1)*a;e.style.height=(a*q)+'px';e.style.width=(a*w)+'px'},o)}
_.json=_.S=function(j,d,t){if(d)return eval('('+j+')');if(!j)return j+'';t=[];if(j.pop){for(x in j)t.push(_.S(j[x]));j='['+t.join(',')+']'}else if(typeof j=='object'){for(x in j)t.push(x+':'+_.S(j[x]));j='{'+t.join(',')+'}'}else if(j.split)j="'"+j.replace(/\'/g,"\\'")+"'";return j}