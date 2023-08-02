package org.hyperledger.besu.cli.options.unstable;

import org.hyperledger.besu.cli.options.CLIOptions;
import org.hyperledger.besu.ethereum.linea.LineaParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import picocli.CommandLine;

/** Linea CLI options. */
public class LineaOptions implements CLIOptions<LineaParameters> {
  private static final String MAX_TX_CALLDATA_SIZE = "--plugin-linea-max-tx-calldata-size";
  private static final String MAX_BLOCK_CALLDATA_SIZE = "--plugin-linea-max-block-calldata-size";

  /**
   * Create linea options.
   *
   * @return the linea options
   */
  public static LineaOptions create() {
    return new LineaOptions();
  }

  @CommandLine.Option(
      hidden = true,
      names = {MAX_TX_CALLDATA_SIZE},
      paramLabel = "<INTEGER>",
      description =
          "If specified, overrides the max size in bytes allowed in the transaction calldata field, specified by the current hard fork")
  private Integer transactionMaxCalldataSize;

  @CommandLine.Option(
      hidden = true,
      names = {MAX_BLOCK_CALLDATA_SIZE},
      paramLabel = "<INTEGER>",
      description =
          "If specified, overrides the max size in bytes of the sum of all transaction calldata fields contained in a block, specified by the current hard fork")
  private Integer blockMaxCalldataSize;

  /**
   * Linea max transaction calldata size.
   *
   * @return optional max transaction calldata size.
   */
  public Optional<Integer> getTransactionMaxCalldataSize() {
    return Optional.ofNullable(transactionMaxCalldataSize);
  }

  /**
   * Linea max block calldata size.
   *
   * @return optional max block calldata size.
   */
  public Optional<Integer> getBlockMaxCalldataSize() {
    return Optional.ofNullable(blockMaxCalldataSize);
  }

  @Override
  public LineaParameters toDomainObject() {
    return new LineaParameters(transactionMaxCalldataSize, blockMaxCalldataSize);
  }

  @Override
  public List<String> getCLIOptions() {
    final List<String> cliOptions = new ArrayList<>(2);
    getTransactionMaxCalldataSize()
        .ifPresent(size -> cliOptions.add(MAX_TX_CALLDATA_SIZE + "=" + size));
    getBlockMaxCalldataSize()
        .ifPresent(size -> cliOptions.add(MAX_BLOCK_CALLDATA_SIZE + "=" + size));
    return cliOptions;
  }
}
