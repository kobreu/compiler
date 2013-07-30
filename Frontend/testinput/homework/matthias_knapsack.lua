-- Version 4.2 Solved on Lua 5.2
-- Accept multiple effort-values

-- Table of Items
-- item = { {<gain>, {<effort>, <effort>,...}}, ...}
-- items = {}

table.reduce = function (list, f) 
	local res = nil
	
	for k, v in pairs(list) do
		if res == nil then
			res = v
		else
			res = f(res, v)
		end 
	end 
	return res 
end

table.merge = function (list, listRef, f)
	res = {}
	if listRef == nil then
		print("nil")
		return
	end
	
	for k,v in pairs(listRef) do
		res[k] = f(list[k], v)
	end
	return res
end

table.map = function (list, f)
	res = {}
	for k, v in pairs(list) do
		res[k] = f(v)
	end
	return res
end

--[[
	solveRecurse takes one item out of the list and try each count of the item. For each try call yourself with the remaining items
		_remainingItemSet			-->	List of the remaining Items
		_remainingCapabilities		-->	List of number of remaining capabilities in this interation step recurse deep
		_reachedGain				--> gain of the best composition until now

	returns
		best gain that is possible
		list with count of the items

]]
function solveRecurse (_remainingItemSet, _remainingCapabilities, _reachedGain)
	--[[
	print("You need better training oder more money for sherpas! Then it's unnecessary to leave specific items at home.")
	]]
	
	if #_remainingItemSet == 0 then
		return _reachedGain, {}
	end

	local currentItem = table.remove(_remainingItemSet)
	
	local gain, lEffort = currentItem[1], currentItem[2]

	local bestCount = 0
	local bestGain = 0
	local bestItemSet = {}
	
	for count = 0, table.reduce(table.merge(_remainingCapabilities, lEffort, function (a,b)
			return  math.floor(a/b) end), function (a,b)
			return a < b and a or b end), 1 do
		
		currentGain, currentItemSet = solveRecurse(_remainingItemSet, table.merge(_remainingCapabilities, 
			table.map(lEffort,
				function (a)
					return a*count end), 
				function (a,b)
					return a-b end), _reachedGain + count*gain)

		if currentGain > bestGain then
			bestGain = currentGain
			bestCount = count
			bestItemSet = currentItemSet
		end
	end

	table.insert(bestItemSet, bestCount)

	table.insert(_remainingItemSet, {gain, lEffort})
	
	return bestGain, bestItemSet
end

function test() 
	

	-- Example from Wikipedia. Result should be 36, {0, 0, 3, 3}
	items = {
		{ 4, {12}},
		{ 2, { 2}},
		{ 2, { 1}},
		{10, { 4}}}
	
	reachedGain, itemSet = solveRecurse(items, {15, 15}, 0)
	
	print("Gain " .. reachedGain)
	print("Item Set " .. #itemSet)
	print("Item Set " .. itemSet[1] .. " " .. itemSet[2] .. " " .. itemSet[3] .. " " .. itemSet[4])
end

test()