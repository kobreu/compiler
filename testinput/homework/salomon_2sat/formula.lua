--[ Formula Class with 2Sat Solver ]--

Graph = require("graph")

Formula = {}

function Formula:new(obj)
	newObj = obj or {}
	self.__index = self
	return setmetatable(newObj, self)
end

function Formula:insertClause(literal1, literal2)
	table.insert(self, {literal1, literal2})
end

function Formula:getSatisfyingAssignment()
	local g = Graph:new()
	
	for a, clause in ipairs(self) do
		assert(a~=nil);
		assert(clause~=nil);
	
		g:insertEdge((-clause[1]), clause[2])
		g:insertEdge((-clause[2]), clause[1])
	end

	local scc = g:getSCC()
	local assignment = {}
	
	for i = #scc, 1, -1 do
		local component = scc[i]
		local literals = {}
	
		for _, vertex in ipairs(component) do
			-- there is a contradiction in this component
			if literals[-vertex.key] then
				return nil
			end
			
			literals[vertex.key] = true
		
			if assignment[vertex.key] == nil then 
				assignment[vertex.key] = false
				assignment[-vertex.key] = true	
			end
		end 
	end 
	
	return assignment
end

function Formula:eval(assignment)
	if assignment == nil then return false end
	
	for a, clause in ipairs(self) do
		local value1 = assignment[clause[1]]
		local value2 = assignment[clause[2]]
		
		
		if (not value1) and (not value2) then
			return false
		end
	end
	
	return true
end

function Formula:selfCheck()
	local formula1 = Formula:new{{1, -1}, {2, 1}, {3, -1}}
	local formula2 = Formula:new{{2, -3}, {3, -1}, {1, -2}, {-1, -1}, {1, 1}}
	
	
	
	assert(formula1:eval(formula1:getSatisfyingAssignment()))
	
	
	assert(not formula2:eval(formula2:getSatisfyingAssignment()))	
	
	
	local function randomLiteral(vars)
		local l = math.random(vars)
	
		if math.random(1, 2) == 2 then 
			return -l
		else
			return l
		end
	end
	
	for i=1,100 do 
		local vars = math.random(3, 25)
		local formula = Formula:new()
		
		repeat
			local l1 = randomLiteral(vars)
			local l2 = randomLiteral(vars)
			formula:insertClause(l1, l2)
			local assignment = formula:getSatisfyingAssignment()

			if assignment then
				assert(formula:eval(assignment))
			end
		until assignment == nil
	end
end

return Formula
