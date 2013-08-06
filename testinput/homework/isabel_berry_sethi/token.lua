OR=1;
AND=2;
STAR=3;
MAYBE=4;
EPSILON=5;
LEAF=6;

function list_iter (l)
	return function ()
		local ret
		if l~=nil then ret=l.value 
		else return nil end
		l = l.next
		return ret
	end
end
