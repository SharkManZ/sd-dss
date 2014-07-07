/*
 * DSS - Digital Signature Services
 *
 * Copyright (C) 2013 European Commission, Directorate-General Internal Market and Services (DG MARKT), B-1049 Bruxelles/Brussel
 *
 * Developed by: 2013 ARHS Developments S.A. (rue Nicolas Bové 2B, L-1253 Luxembourg) http://www.arhs-developments.com
 *
 * This file is part of the "DSS - Digital Signature Services" project.
 *
 * "DSS - Digital Signature Services" is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * DSS is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * "DSS - Digital Signature Services".  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.europa.ec.markt.dss.validation102853;

import java.util.Date;
import java.util.List;
import java.util.Set;

import eu.europa.ec.markt.dss.DigestAlgorithm;
import eu.europa.ec.markt.dss.EncryptionAlgorithm;
import eu.europa.ec.markt.dss.signature.DSSDocument;
import eu.europa.ec.markt.dss.signature.SignatureLevel;
import eu.europa.ec.markt.dss.validation102853.bean.CandidatesForSigningCertificate;
import eu.europa.ec.markt.dss.validation102853.bean.CertifiedRole;
import eu.europa.ec.markt.dss.validation102853.bean.CommitmentType;
import eu.europa.ec.markt.dss.validation102853.bean.SignatureCryptographicVerification;
import eu.europa.ec.markt.dss.validation102853.bean.SignatureProductionPlace;
import eu.europa.ec.markt.dss.validation102853.certificate.CertificateRef;
import eu.europa.ec.markt.dss.validation102853.crl.CRLRef;
import eu.europa.ec.markt.dss.validation102853.crl.OfflineCRLSource;
import eu.europa.ec.markt.dss.validation102853.ocsp.OCSPRef;
import eu.europa.ec.markt.dss.validation102853.ocsp.OfflineOCSPSource;

/**
 * Provides an abstraction for an Advanced Electronic Signature. This ease the validation process. Every signature
 * format : XAdES, CAdES and PAdES are treated the same.
 *
 * @version $Revision: 1820 $ - $Date: 2013-03-28 15:55:47 +0100 (Thu, 28 Mar 2013) $
 */
public interface AdvancedSignature {

	/**
	 * Specifies the format of the signature
	 */
	public SignatureForm getSignatureForm();

	/**
	 * Retrieves the signature algorithm (or cipher) used for generating the signature.
	 * XAdES: http://www.w3.org/TR/2013/NOTE-xmlsec-algorithms-20130411/
	 *
	 * @return
	 */
	public EncryptionAlgorithm getEncryptionAlgo();

	/**
	 * Retrieves the signature algorithm (or cipher) used for generating the signature.
	 * XAdES: http://www.w3.org/TR/2013/NOTE-xmlsec-algorithms-20130411/
	 *
	 * @return
	 */
	public DigestAlgorithm getDigestAlgo();

	/**
	 * Returns the signing time information.
	 *
	 * @return
	 */
	public Date getSigningTime();

	/**
	 * Gets a certificate source which contains ALL certificates embedded in the signature.
	 *
	 * @return
	 */
	public SignatureCertificateSource getCertificateSource();

	/**
	 * Gets a CRL source which contains ALL CRLs embedded in the signature.
	 *
	 * @return
	 */
	public OfflineCRLSource getCRLSource();

	/**
	 * Gets an OCSP source which contains ALL OCSP responses embedded in the signature.
	 *
	 * @return
	 */
	public OfflineOCSPSource getOCSPSource();

	/**
	 * Gets an object containing the signing certificate or information indicating why it is impossible to extract it
	 * from the signature. If the signing certificate is identified then it is cached and the subsequent calls to this
	 * method will return this cached value. This method never returns null.
	 *
	 * @return
	 */
	public CandidatesForSigningCertificate getCandidatesForSigningCertificate();

	public List<String> getInfo();

	/**
	 * This method returns the signing certificate token or null if there is no valid signing certificate.
	 *
	 * @return
	 */
	public CertificateToken getSigningCertificateToken();

	/**
	 * Verifies the signature integrity; checks if the signed content has not been tampered with.
	 *
	 * @param detachedDocument the original document concerned by the signature if not part of the actual object.
	 * @return SignatureCryptographicVerification with all the information collected during the validation process.
	 */
	public SignatureCryptographicVerification checkIntegrity(final DSSDocument detachedDocument);

	/**
	 * Verifies the signature integrity; checks if the signed content has not been tampered with.
	 *
	 * @param detachedDocument           the original document concerned by the signature if not part of the actual object.
	 * @param providedSigningCertificate In case of a non AdES signature the signing certificate can be provided.
	 * @return SignatureCryptographicVerification with all the information collected during the validation process.
	 */
	public SignatureCryptographicVerification checkIntegrity(final DSSDocument detachedDocument, final CertificateToken providedSigningCertificate);

	/**
	 * Returns the Signature Policy OID from the signature.
	 *
	 * @return
	 */
	public SignaturePolicy getPolicyId();

	/**
	 * Return information about the place where the signature was generated
	 *
	 * @return
	 */
	public SignatureProductionPlace getSignatureProductionPlace();

	public CommitmentType getCommitmentTypeIndication();

	/**
	 * Returns the content type of the signed data
	 *
	 * @return
	 */
	public String getContentType();

	/**
	 * @return
	 */
	public abstract String getContentIdentifier();

	/**
	 * @return
	 */
	public abstract String getContentHints();

	/**
	 * Returns the claimed role of the signer.
	 *
	 * @return
	 */
	public String[] getClaimedSignerRoles();

	/**
	 * Returns the certified role of the signer.
	 *
	 * @return
	 */
	public List<CertifiedRole> getCertifiedSignerRoles();

	/**
	 * Get certificates embedded in the signature
	 *
	 * @reutrn a list of certificate contained in the signature
	 */
	public List<CertificateToken> getCertificates();

	/**
	 * Returns the content timestamps
	 *
	 * @return
	 */
	public List<TimestampToken> getContentTimestamps();

	/**
	 * Returns the content timestamp data (timestamped or to be).
	 *
	 * @param timestampToken
	 * @return {@code byte} array representing the canonicalized data to be timestamped
	 */
	public byte[] getContentTimestampData(final TimestampToken timestampToken);

	/**
	 * Returns the signature timestamps
	 *
	 * @return
	 */
	public List<TimestampToken> getSignatureTimestamps();

	/**
	 * Returns the data (signature value) that was timestamped by the SignatureTimeStamp for the given timestamp.
	 *
	 * @param timestampToken
	 * @return {@code byte} array representing the canonicalized data to be timestamped
	 */
	public byte[] getSignatureTimestampData(final TimestampToken timestampToken);

	/**
	 * Returns the time-stamp which is placed on the digital signature (XAdES example: ds:SignatureValue element), the
	 * signature time-stamp(s) present in the AdES-T form, the certification path references and the revocation status
	 * references.
	 */
	public List<TimestampToken> getTimestampsX1();

	/**
	 * Returns the data to be time-stamped. The data contains the digital signature (XAdES example: ds:SignatureValue
	 * element), the signature time-stamp(s) present in the AdES-T form, the certification path references and the
	 * revocation status references.
	 *
	 * @return {@code byte} array representing the canonicalized data to be timestamped
	 */
	public byte[] getTimestampX1Data(final TimestampToken timestampToken);

	/**
	 * Returns the time-stamp which is computed over the concatenation of CompleteCertificateRefs and
	 * CompleteRevocationRefs elements (XAdES example).
	 *
	 * @return
	 */
	public List<TimestampToken> getTimestampsX2();

	/**
	 * Returns the data to be time-stamped which contains the concatenation of CompleteCertificateRefs and
	 * CompleteRevocationRefs elements (XAdES example).
	 *
	 * @return {@code byte} array representing the canonicalized data to be timestamped
	 */
	public byte[] getTimestampX2Data(final TimestampToken timestampToken);

	/**
	 * Returns the archive TimeStamps
	 *
	 * @return
	 */
	public List<TimestampToken> getArchiveTimestamps();

	/**
	 * Archive timestamp seals the data of the signature in a specific order. We need to retrieve the data for each
	 * timestamp.
	 *
	 * @param timestampToken null when adding a new archive timestamp
	 * @return {@code byte} array representing the canonicalized data to be timestamped
	 */
	public byte[] getArchiveTimestampData(final TimestampToken timestampToken);

	/**
	 * Returns a list of counter signatures applied to this signature
	 *
	 * @return a list of AdvancedSignatures representing the counter signatures
	 */
	public List<AdvancedSignature> getCounterSignatures();

	/**
	 * Returns the digest value of the certification path references and the revocation status references. (XAdES
	 * example: CompleteCertificateRefs and CompleteRevocationRefs elements)
	 */
	public List<TimestampReference> getTimestampedReferences();

	/**
	 * Retrieve list of certificate ref
	 *
	 * @return
	 */
	public List<CertificateRef> getCertificateRefs();

	/**
	 * @return The list of CRLRefs contained in the Signature
	 */
	public List<CRLRef> getCRLRefs();

	/**
	 * @return The list of OCSPRef contained in the Signature
	 */
	public List<OCSPRef> getOCSPRefs();

	/**
	 * This method returns the DSS unique signature id. It allows to unambiguously identify each signature.
	 *
	 * @return The signature unique Id
	 */
	public String getId();

	/**
	 * Returns the set of digest algorithms used to build the certificate's digest. For example, these digests are
	 * referenced in CompleteCertificateRefs in the case of XAdES signature.
	 *
	 * @return
	 */
	public Set<DigestAlgorithm> getUsedCertificatesDigestAlgorithms();

	/**
	 * @param signatureLevel
	 * @return true if the signature contains the data needed for this signatureLevel. Doesn't mean any validity of the data found.
	 */
	boolean isDataForSignatureLevelPresent(final SignatureLevel signatureLevel);

	SignatureLevel getDataFoundUpToLevel();

	/**
	 * @return the list of signature levels for this kind of signature, in the simple to complete order. Example: B,T,LT,LTA
	 */
	SignatureLevel[] getSignatureLevels();

	void prepareTimestamps(ValidationContext validationContext);

	void validateTimestamps();

}
