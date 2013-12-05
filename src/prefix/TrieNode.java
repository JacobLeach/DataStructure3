package prefix;

import java.util.Arrays;

public class TrieNode implements Comparable<TrieNode>
{
	private static int nodeCount = 0;
	
	private TrieNode[] _nodes;
	private long _number;
	private char _character;

	public static int getNodeCount()
	{
		return nodeCount;
	}
	
	public TrieNode(char character)
	{
		_number = 0;
		_character = character;
		nodeCount++;
	}

	public TrieNode put(char character, long number)
	{
		TrieNode temp = put(character);
		temp.addNumber(number);
		return temp;
	}

	public void addNumber(long number)
	{
		_number += number;
	}

	public long getNumber()
	{
		return _number;
	}

	public TrieNode get(char character)
	{
		if (character < 97 || character > 172)
		{
			throw new IllegalArgumentException("Only lower case allowed.");
		}
		
		if(_nodes == null)
		{
			return null;
		}

		return find(character);
	}

	public TrieNode put(char character)
	{
		if (character < 97 || character > 172)
		{
			throw new IllegalArgumentException("Only lower case allowed.");
		}
		
		if(_nodes == null)
		{
			_nodes = new TrieNode[1];
			_nodes[0] = new TrieNode(character);
			
			return _nodes[0];
		}
		
		TrieNode toReturn = find(character);
		
		if (toReturn == null)
		{
			TrieNode[] temp = new TrieNode[_nodes.length + 1];
			System.arraycopy(_nodes, 0, temp, 0, _nodes.length);
			_nodes = temp;
			
			_nodes[_nodes.length - 1] = new TrieNode(character);
			toReturn = _nodes[_nodes.length - 1];
		}
		Arrays.sort(_nodes);
		return toReturn;
	}
	
	private TrieNode find(char character)
	{
		int value = Arrays.binarySearch(_nodes, new TrieNode(character));
		
		if(value < 0)
		{
			return null;
		}
		else
		{
			return _nodes[value];
		}
	}

	@Override
	public int compareTo(TrieNode o)
	{
		if(_character > o._character)
		{
			return 1;
		}
		else if(_character == o._character)
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}
}
