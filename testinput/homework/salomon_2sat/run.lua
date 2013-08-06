-- print an array
function print_formula(a)
	for i,v in ipairs(a) do
		local print1 = v[1]
		if(v[1] < 0) then
			print1 = "not " .. (-v[1])
		end
		local print2 = v[2]
		if(v[2] < 0) then
			print2 = "not " .. (-v[2])
		end
		print("(" .. print1 .. " or " .. print2 .. ")")
	end
end

function print_assignment(a)
	for a, clause in ipairs(a) do
		print(a);
		print(clause);
		--print(a .. ": " .. clause);		
	end
end

formula = require("formula")

local formula1 = Formula:new{{1, -1}, {2, 1}, {3, -1}}

print("METHODS: Formula:new{{1,2}, ...}, formula:getSatisfyingAssignment(), formula:eval(assignment), print_formula(formula), print_assignment(assignment)");

print("IN:");

print_formula(formula1);

local assignment = formula1:getSatisfyingAssignment();

print("OUT:");

print_assignment(assignment);

local eval = formula1:eval(assignment);

print("FORMULA IS:")
print(eval);


