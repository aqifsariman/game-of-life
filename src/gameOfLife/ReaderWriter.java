package gameOfLife;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReaderWriter {
    private FileReader reader;
    BufferedReader bufferReader;
    FileWriter writer;
    Path path;

    public ReaderWriter(String path) throws FileNotFoundException {
        this.path = Paths.get(path);
    }

    public void readFile(String name) throws FileNotFoundException, IOException {
        File file = Paths.get(Constants.GOL_PATH + String.format("/%s.gol", name)).toFile();
        this.reader = new FileReader(file);
        this.bufferReader = new BufferedReader(reader);
    }

    public String bufferedFile() throws IOException {
        return this.bufferReader.readLine();
    }

    public void closeBuffer() throws IOException {
        this.bufferReader.close();
    }

    public void closeReader() throws IOException {
        this.reader.close();
    }

}
