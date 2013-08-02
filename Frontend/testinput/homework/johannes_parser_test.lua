marker = 1

current_symbol = function()
	return string.sub(input, marker,marker)
end

increase_marker = function()
	marker = marker + 1
	return true
end

function E()

	current = current_symbol()
	
	-- empty set ∅
	if current == 'o' then
		increase_marker()
		return true
	end
	
	-- nullstring λ
	if current == 'l' then
		increase_marker()
		return true
	end
	
	-- a in Σ
	if current == 'a' then
		increase_marker()
		return true
	end
	
	if C() then
		return true
	end
	
	return false
	
end

function C()
	local marker_backup = marker
	
	-- (E+E)
	if ('(' == current_symbol()) and increase_marker() and E() and
		('+' == current_symbol()) and increase_marker() and E() and
		(')' == current_symbol()) and increase_marker() then
			return true
	else
		marker = marker_backup
	end
	
	-- (EE)
	if (current_symbol() == '(') and increase_marker() and
		E() and E() and (current_symbol() == ')') and increase_marker() then
			return true
	else
		marker = marker_backup
	end
	
	-- (E*)
	if (current_symbol() == '(') and increase_marker() and
		E() and (current_symbol() == '*') and increase_marker() and
		(current_symbol() == ')') and increase_marker() then
			return true
	else
		marker = marker_backup
	end
	
	-- (E)
	if (current_symbol() == '(') and increase_marker() and E() and
		(current_symbol() == ')') and increase_marker() then
			return true
	else
		marker = marker_backup
	end
	
	return false

end

--[[
 Program start
--]]

-- Check command line input
if (arg[1]) then
	input = arg[1]
else
	input = '(a+a)$'
end

-- Add a dollar to the end of the string
if string.sub(input, -1) ~= '$' then
	input = input .. '$'
end

print('parsing ' .. input .. ' ..')

if (E() and (current_symbol() == '$')) then
	print('parse succesful')
else
	print('parse failed')
end
