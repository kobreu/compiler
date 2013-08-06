
-- Check for an array if it is sorted and print the result
function is_sorted(a)
	for i = 1,(#(a))-1,1 do
		if(a[i]>a[i+1]) then
			print ("false")
			return
		end
	end
	print ("true")
	return
end

-- print an array
function print_array(a)
	for i,v in ipairs(a) do
		print(i .. ": " .. v)
	end
end

-- swap two values in an array
function swap(a,i,j)
	tmp = a[i]
	a[i]=a[j]
	a[j]=tmp
end

-- class Sortable to create an object with an array reference that can be modified
Sortable = {}
Sortable.__index = Sortable

function Sortable.create(a)
	local sortable = {}
	setmetatable(sortable, Sortable)
	sortable.array = a
	return sortable
end

function Sortable:getArray()
	return self.array
end

function Sortable:setArray(a)
	self.array = a
end

-- returns the merged of the two arrays
function merge(left,right)
	local i=1;local j=1
	local merged={}
	while (i <= (#left)) and (j <= (#right)) do
		if(left[i]<=right[j]) then
			merged[(#merged)+1] = left[i]
			i = i + 1
		else
			merged[(#merged)+1] = right[j]
			j = j +1
		end
	end
	while i <= (#left) do
		merged[(#merged)+1] = left[i]
		i = i + 1

	end
	while j <= (#right) do
		merged[(#merged)+1] = right[j]
		j = j+1
	end
	return merged
end

-- recursive implementation of mergesort
-- gets an instance of sortable, and the delimiters (inclusive)
function mergesort_rec(a,left,right)
	-- print(left .. " " .. right);
	if(left >= right) then
		return {a:getArray()[left]}
	else
		local mid = math.floor((left+right)/2)
		left = mergesort_rec(a, left, mid)
		right = mergesort_rec(a, mid+1,right)
		merged = merge(left, right)
		return merged
	end
end

-- mergesort
-- expects an instance of Sortable
function mergesort(a)
	assert(a:getArray(), "Not a Sortable!")
	a:setArray(mergesort_rec(a, 1, #a:getArray()))
end

-- takes the last one as pivot element
-- returns index of the pivot element
-- left: <= pivot, right: > pivot
function split(a, left, right)
	i = left
	j = right-1
	pivot = a[right]
	

	repeat
	
		while ((a[i] <= pivot) and (i < right)) do
			i = i + 1
		end
		
		while ((a[j] >= pivot) and (j > left)) do
			j = j - 1
		end
		if i < j then
			swap(a,i,j)
		end
	until i >= j
	
	if a[i] > pivot then
		swap(a, i, right)
	end

	return i
	end

-- sort from left, right, both inclusive
function quicksort_rec(a,left, right)
	if left < right then
		splitter = split(a, left, right)
		quicksort_rec(a, left, splitter-1)
		quicksort_rec(a, splitter+1, right)
	end
end

function quicksort(a)
	quicksort_rec(a,1, #(a))
end

-- simple bubblesort implementation
function bubblesort(a)
	for i = 1,#(a),1 do
		for j = i, #(a),1 do
			if(a[i]>a[j]) then
				swap(a,i,j)
			end
		end
	end
end

-- sorts with the LUA library sort algorithm
function librarySort(a)
	table.sort(a, function(n1, n2)
		return n1 < n2
	end)
end

-- https://en.wikipedia.org/wiki/Stooge_sort
function stoogesort_rec(a, left, right)
	print(left .. " " .. right)
	if a[right] < a[left] then
		swap(a,left,right)
	end
	if (right-left+1) >= 3 then
		local t = math.floor((right-left+1)/3)
		stoogesort_rec(a,left,right-t)
		stoogesort_rec(a, left+t, right)
		stoogesort_rec(a, left, right-t)
	end
end

function stoogesort(a)
	stoogesort_rec(a,1,(#a))
end


a={99,101,29,192,21,4,52,15,2,51,25,125,1,2,64,3,6,73,73,47,3,321,1}
bubblesort(a)


is_sorted(a)


b={}
b["a"]=2
b["c"]=3
bubblesort(b)
is_sorted(b)


c={152,6,32,6,234,7,2,7,89,457,347,3,62,35,12,1,51,125,12,51,6,4326,3}
quicksort(c)
is_sorted(c)


d={}
quicksort(d)
is_sorted(d)

e = {12581,25,12,5,15,1,25,6,2,6,6,6,236,12,1,5}
librarySort(e)
is_sorted(e)

f = {12552,21,2,25,2515}
-- give f the function to sort itself
f.sort = function() librarySort(f) end
f.sort()
is_sorted(f)

-- another syntax to define this
g = {21952,21,2,251}
function g.sort () librarySort(g) end
g.sort()
is_sorted(g)

h = {12128592,2,15,25,12,51,62,436,236,23,52,1,25,23,3,46}
sort = Sortable.create(h)
mergesort(sort)
is_sorted(sort:getArray())

-- does not work
--i = {2118,2,15,2,43,63,46,632,62,11,526,473,3,4,46,23,5,1,251,21,12,52,6,32,12}
--stoogesort(i)
--is_sorted(i)


print("USAGE: i = {1,15,2,...}, quicksort(i), bubblesort(i), print_array(i), is_sorted(i)");
print("INPUT: i = {5,4,3,2,1}");
k = {5,4,3,2,1}
quicksort(k);
print("OUTPUT:");
print_array(k);
print("is sorted?");
print (is_sorted(k));