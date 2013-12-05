package prefix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FastPrefixDictionary implements PrefixDictionary
{
	public FastPrefixDictionary(String file) throws NumberFormatException, IOException
	{
		_head = new TrieNode(' ');

		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

		String line = null;
		String[] split = new String[2];
		int index0;

		while ((line = bufferedReader.readLine()) != null)
		{
			index0 = line.indexOf(',');
			split[0] = line.substring(0, index0).trim();
			split[1] = line.substring(index0 + 1).trim();
			add(split[0], Long.parseLong(split[1]));

		}
		bufferedReader.close();
	}

	@Override
	public long sum(String prefix)
	{
		return getNumber(prefix);
	}

	private TrieNode _head;

	public void add(String word, long number)
	{
		word = word.toLowerCase();

		TrieNode current = _head;
		current.addNumber(number);

		for (int i = 0; i < word.length(); i++)
		{
			current = current.put(word.charAt(i), number);
		}
	}

	public long getNumber(String prefix)
	{
		prefix = prefix.toLowerCase();

		TrieNode current = _head;

		for (int i = 0; i < prefix.length(); i++)
		{
			current = current.get(prefix.charAt(i));

			if (current == null)
			{
				return 0;
			}
		}

		return current.getNumber();
	}
}
