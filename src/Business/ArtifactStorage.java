package Business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ArtifactStorage {
	
	public static List<Artifact> artifactList = new ArrayList<Artifact>();;
	
	public ArtifactStorage()
	{
		initializeStorage();
	}
	
	private void initializeStorage()
	{
		// initialize all artifacts
		Artifact.initializeArtifacts();
		
		// At the beginning of the game, 
		// the artifact storage will hold all the artifacts
		// Add all artifacts to the existing list
		artifactList.addAll(Artifact.artifacts);
		artifactList.addAll(Artifact.artifacts);
		artifactList.addAll(Artifact.artifacts);
		
		System.out.println("Artifact storage initialized.");

	}
	
	public void shuffleArtifacts()
	{
		Collections.shuffle(artifactList);
        System.out.println("Artifacts shuffled.");
	}
	
	public static Artifact getArtifact() {

		//Remove and return top:
		if(artifactList.isEmpty()) return null; // throw exception
		return artifactList.remove(0);

	}

}
