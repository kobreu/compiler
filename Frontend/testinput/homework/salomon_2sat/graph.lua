--[ Graph Class with Tarjan's SCC algorithm ]--

Graph = {}

function Graph:new() 
	newObj = {vertices = {}, edges = {}}
	self.__index = self
	return setmetatable(newObj, self)
end

function Graph:insertVertex(v)
	local vertex = self.vertices[v]
	
	if not vertex then
		vertex = {key = v}
		self.vertices[v] = vertex
		self.edges[vertex] = {}
	end
	
	return vertex
end

function Graph:insertEdge(v, w)
	local vVertex = self:insertVertex(v)
	local wVertex = self:insertVertex(w)
	
	row = self.edges[vVertex]
	row[wVertex] = true;
end

-- Computes all strongly connected components using Tarjan's algorithm
function Graph:getSCC()	
	local function strongconnect (g, v)
		v.index = index
		v.lowlink = index
		index = index + 1
		table.insert(S, v)
		S[v] = true -- Reverse lookup
	
		for w in pairs(g.edges[v]) do
			if not w.index then
				strongconnect(g, w)
				v.lowlink = math.min(v.lowlink, w.lowlink)
			elseif S[w] then
				v.lowlink = math.min(v.lowlink, w.index)
			end
		end

		if v.lowlink == v.index then
			component = {}
		
			repeat 
				w = table.remove(S)
				S[w] = nil
				table.insert(component, w)
			until w == v

			table.insert(SCC, component)
		end
	end

	index = 0
 	S = {}
 	SCC = {}
 	
	for _, v in pairs(self.vertices) do
		if not v.index then
			strongconnect(self, v)
		end
	end
	
	-- Release Memory
	
	index = nil
	S = nil
	
	for _, v in pairs(self.vertices) do
		v.index = nil
		v.lowlink = nil
	end
	
	return SCC
end

function Graph:selfCheck() 
	g = Graph:new()
	
	g:insertEdge(1, 2)
	g:insertEdge(2, 3)
	g:insertEdge(3, 4)
	g:insertEdge(4, 2)
	g:insertEdge(4, 5)
	g:insertEdge(5, 6)
	g:insertEdge(6, 5)
	
	scc = g:getSCC()
	
	assert(#scc == 3)
	assert(#scc[1] == 2)
	assert(#scc[2] == 3)
	assert(#scc[3] == 1)
	assert(scc[1][1].key == 5 or scc[1][1].key == 6)
	assert(scc[1][2].key == 5 or scc[1][2].key == 6)
	assert(scc[2][1].key == 2 or scc[2][1].key == 3 or scc[2][1].key == 4)
	assert(scc[2][2].key == 2 or scc[2][2].key == 3 or scc[2][2].key == 4)
	assert(scc[2][3].key == 2 or scc[2][3].key == 3 or scc[2][3].key == 4)
	assert(scc[3][1].key == 1)
end

return Graph
