package Business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArtifactStorage {
	
	public List<Artifact> artifactList = new ArrayList<Artifact>();;
	
	public ArtifactStorage()
	{
		initializeStorage();
		
		System.out.println("Artifact storage has been created.");
	}
	
	private void initializeStorage()
	{
		// initialize all artifacts
		Artifact.initializeArtifacts();
		
		// At the beginning of the game, 
		// the artifact storage will hold all the artifacts
		// Add all artifacts to the existing list
		artifactList.addAll(Artifact.artifacts);
		
		System.out.println("Artifact storage artifact list has been initialized.");
		for (Artifact a: artifactList)
		{
			System.out.println(a.getClass().getCanonicalName());
		}
	}
	
	public void shuffleArtifacts()
	{
		Collections.shuffle(artifactList);
        System.out.println("Ingredients have been shuffled.");
	}
	
	public Artifact getRandomArtifact() {
		
		int max = artifactList.size() - 1;
		int min = 0;
		
		// Generate random int value from min to max
	    int randomInt = (int)Math.floor(Math.random() * ( - min + 1) + max);
	    
	    return artifactList.remove(randomInt);
	}

	public static Artifact removeRandomArtifact() {
		return null;
	}
}
