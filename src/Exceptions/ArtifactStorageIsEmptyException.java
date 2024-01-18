package Exceptions;

public class ArtifactStorageIsEmptyException extends Exception {

	private static final long serialVersionUID = 1L;

	public ArtifactStorageIsEmptyException() {
		super("Artifact Storage is empty!");
	}
	
	public ArtifactStorageIsEmptyException(String message) {
		super(message);
	}

}
